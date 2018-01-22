(function ($) {

    'use strict';

    var HFSelect = function($el,options){

        var $this = this;
        $this.options = $.extend(HFSelect.DEFAULTS,options||{});


        $this.$el = $el;

        $this.$wrapper = $(sprintf(
            '<div class="hf-wrapper %s %s">',
            $el.attr('class') || '',
            sprintf('title="%s"',$el.attr('title'))
        ));
        $this.$wrapper.addClass("hf-select");
        $this.$input = $(sprintf(
            '<div class="hf-input"  placeholder="%s"/>',
            this.options.placeholder
        ));
        $this.$input.css($this.options.inputCss);

        $this.$choices = $('<span class="hf-choices"></span>');

        $this.$drop = $('<div class="hf-drop" style="display: none"></div>');
        if($this.options.isOpen){
            $this.$drop.show();
        }

        $this.$input.append($this.$choices)
        $this.$el.after($this.$wrapper);
        $this.$wrapper.append($this.$input);
        $this.$wrapper.append($this.$drop);

        $el.hide()

    };

    var sprintf = function (str) {
        var args = arguments,
            flag = true,
            i = 1;

        str = str.replace(/%s/g, function () {
            var arg = args[i++];

            if (typeof arg === 'undefined') {
                flag = false;
                return '';
            }
            return arg;
        });
        return flag ? str : '';
    };

    HFSelect.DEFAULTS={
        multiple:false,
        size:1,
        autoWidth:false,
        isOpen:false,
        placeholder:'',
        animation: {
            animate:"slide",
            speed:"fast"
        },
        inputCss:{},
        choiceTemplate : $('<span class="hf-choice"><i class="hf-close" aria-hidden="true"></i></span>')
    };

    HFSelect.prototype = {
        constructor:HFSelect,
        init:function(){
            var that = this,
                $ul = $('<ul></ul>');

            this.$drop.html('');
            $.each(this.$el.children(),function(i,elm){
                $ul.append(that.optionToHtml(i,elm))
            });
            this.$drop.append($ul);
            this.events();
        },
        events: function () {
            var $this = this;

            this.$input
                .off('click').on('click',function(e){
                    e.stopPropagation();
                    var status = $this.$wrapper.hasClass("open"),
                        newStatus = $this.options.isOpen?'close':'open';
                    if(status!=newStatus){
                        $this[newStatus]();
                    }
            })

            if($this.options.multiple){

            }
            this.$wrapper.off('keydown').on('keydown', function (e) {
                switch (e.which) {
                    case 27:
                        $this.close();
                        $this.$input.focus();
                        break;
                }
            });

            this.$wrapper.find("ul.hf-options li").off("click").on("click",function(e){
                // get value  and set value to input
                e.preventDefault();
                e.stopPropagation();
                var text = $(this).text() || '',template = $this.options.choiceTemplate.clone();

                template.attr("value",text);
                if($this.options.multiple){
                    $this.options.isOpen = true;
                    template.html(text + template.html());
                    var length = $this.$choices.children().length;
                    if(!$this.isExit(text) && length < $this.options.size||0){
                        $this.$choices.append(template);
                    }

                    template.find(".hf-close").off("click").on("click",function(e){
                        e.preventDefault();
                        e.stopPropagation();
                        var element = $(this).closest("span.hf-choice");
                        element.remove();
                    })

                }else{
                    $this.options.isOpen = false;
                    template.removeClass("hf-choice");
                    $this.$choices.html('');
                    template.html(text)
                    $this.$choices.append(template);
                }

            })

            $(".hf-wrapper.hf-select .hf-drop").off("click").on("click",function(e){
                e.preventDefault();
                e.stopPropagation();
            });
            $(document).on("click",function(){
                $this.close();
            })
        },
        isExit:function(val){
            var $this = this,isExit = false;
            $this.$choices.children().each(function(i,v){
                if(isExit){
                    return;
                }
                var value = $(this).attr("value");
                if(value == val){
                    isExit = true;
                }
            })
            return isExit;
        },
        optionToHtml:function(i,elm){
            var $elm =  $(elm),$this = this,
                classes = $elm.attr('class') || '',
                multiple = this.options.multiple?'multiple':'',
                text = $(elm).text(),$ul=$('<ul class="hf-options"></ul>'),$el;

            if($elm.is('.hf-group')){
                text = $elm.attr("label");
                $el = $(sprintf('<li class="%s"><span>%s</span></li>',classes,text));
                $.each($elm.children(),function(ci,celm){
                    $ul.append($this.optionToHtml(ci,celm));
                });
                $el.append($ul);
                return $el;
            }
            if($elm.is('option')){
                $el = [
                    sprintf('<li class="%s %s">',multiple,classes),
                    sprintf('<span>%s</span>',text),
                    '</li>'
                ].join('');
            }
            return $el;

        },
        open:function(){
            var speed = this.options.animation.speed;
            var open = this.options.open;
            this.options.isOpen = true;
            this.$wrapper.addClass('open');
            this.$drop[this.animateMethod('show')](speed);

            if(!this.options.autoWidth){
                var width = this.$input.width();
                this.$drop.css("width",width)
            }

            if(this.options.container){
                var offset = this.$drop.offset();
                this.$drop.appendTo($(this.options.container))
                this.$drop.offset({
                    top:offset.top,
                    left:offset.left
                })
            }

            open && open();
        },
        close:function(){
            var speed = this.options.animation.speed;
            var close = this.options.close;
            this.options.isOpen = false;
            this.$wrapper.removeClass('open');
            this.$drop[this.animateMethod('hide')](speed);
            if(this.options.container){
                this.$parent.append(this.$drop);
                this.$drop.css({
                    'top':'auto',
                    'left':'auto'
                })
            }
            close && close();
        },
        animateMethod:function (method) {
            var methods = {
                show: {
                    fade: 'fadeIn',
                    slide: 'slideDown'
                },
                hide: {
                    fade: 'fadeOut',
                    slide: 'slideUp'
                }
            };
            return methods[method][this.options.animation.animate] || method;
        },
        enable:function(){

        },
        diabled:function(){

        },
        refresh:function(){
            this.init();
        }
    }

    $.fn.hfSelect = function(){
        var option = arguments[0],
            args = arguments,
            value;
        this.each(function(){
            var $this = $(this),
                data = $this.data('hfSelect'),
                options = $.extend({},HFSelect.DEFAULTS,$this.data(),typeof option == 'object' && option)
            if(!data){
                data = new HFSelect($this,options);
                    $this.data('hfSelect',data);
            }

            if(typeof option === 'string'){
                value = data[option] && data[option](args[1]);
            }else{
                data.init();
                if(args[1]){
                    value = data[args[1]].apply(data,[].slice.call(args,2))
                }
            }

        });
        return typeof value !== 'undefined' ?value:this;
    };
    $.fn.hfSelect.Constructor = HFSelect;

    // $(document)
    //     .data('keycount', 0)
    //     .on('keydown.bs.select', '.bootstrap-select [data-toggle=dropdown], .bootstrap-select [role="listbox"], .bs-searchbox input', Selectpicker.prototype.keydown)
    //     .on('focusin.modal', '.bootstrap-select [data-toggle=dropdown], .bootstrap-select [role="listbox"], .bs-searchbox input', function (e) {
    //         e.stopPropagation();
    //     });

    // SELECTPICKER DATA-API
    // =====================
    /*$(window).on('load', function () {
        $('.hf-select').each(function () {
            var $hfselect = $(this);
            Plugin.call($hfselect, $hfselect.data());
        })
    });*/

})(jQuery);