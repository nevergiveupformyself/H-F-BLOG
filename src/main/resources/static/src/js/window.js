/*
dialog
 */
(function($,hf){
    "use strict";
    var Window = function(element,options){
        this.$element = element;
        this.options = $.extend(Window.DEFAULTS,options);
    }

    Window.DEFAULTS = {
        title:"",
        name:"Window",
        action:['close'],
        autoFocus:true,
        modal:false,
        appendTo:"body",
        width:null,
        height:null,
        position:{},
        wrapper:""
    };

    Window.prototype.init = function(){
        this.wrapper();

    };

    Window.prototype.open = function(){

    }
    Window.prototype.wrapper = function(){
        var $this = this,options = $this.options;
        $this.wrapper = $('<div class="hf-window hf-wrapper"><div class="hf-title"></div><div class="hf-content"></div></div>');
        $this.appendTo(options.appendTo);
    };

    $.fn.Window   = function(options){
        var $element = this;
        return  new Window($element,options);
    };

    $.fn.Window.Constructor =  Window;

}(jQuery))