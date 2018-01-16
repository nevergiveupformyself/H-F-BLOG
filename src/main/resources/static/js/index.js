/**
* home.js
* */
var initImgCover = function(){
    var directionObj = {
        "top-left":"rotate(-90deg)",
        "left-top":"rotate(90deg)",
        "top-right":"rotate(90deg)",
        "right-top":"rotate(-90deg)",
        "bottom-left":"rotate(90deg)",
        "left-bottom":"rotate(-90deg)",
        "bottom-right":"rotate(-90deg)",
        "right-bottom":"rotate(90deg)"
    };
    function transFormByDirection(element,direction,isEnter){

        var currentCoverName= element.attr("cover-name")||"",item = element.closest("li"),currentCover;
        function transFormCover(defaultName){
            var name = null;
            var arr = ["top","right","bottom","left"];
            for(var i=0;i< arr.length;i++){
                if(element.hasClass(arr[i])){
                    name = direction +"-"+ arr[i];
                    break;
                }
            }

            if(!name || !directionObj[name]){
                name = defaultName;
            }

            element.removeClass("top left right bottom").addClass(direction);
            if(isEnter){
                currentCoverName = name;
                currentCover = item.find(".hf-clone."+currentCoverName);
                currentCover.addClass("hf-hover");

            }else{
                var cssText = item.find(".hf-clone."+name)[0].style.cssText;
                currentCover = item.find(".hf-clone."+currentCoverName);
                currentCover[0].style.cssText = cssText;
                currentCover.removeClass("hf-hover");
                currentCoverName = name;
            }
            element.attr("cover-name",currentCoverName);
        }
        function getDefaultCoverName(defaultName,num){
            var name = defaultName[0],coverName="";
            if(isEnter){
                var  ul = item.closest("ul"),index = item.index() +num,prevItem,prevCoverName;
                if(index >=0 && index < 9 ){
                    prevItem = ul.children("li:eq("+index+")");
                    prevCoverName = prevItem.find(".hf-cover:not(.hf-clone)").attr("cover-name");
                    if(prevCoverName){
                        coverName = direction + "-"+ prevCoverName.split("-")[1];
                    }
                }
            }else{
                coverName = direction + "-"+currentCoverName.split("-")[1];
            }
            if(defaultName.indexOf(coverName)!= -1){
                name = coverName;
            }

            return name;
        }

        switch (direction){
            case "top":
                // -3
                transFormCover( getDefaultCoverName(["top-right","top-left"],-3));
                break;
            case "right":
                // +1
                transFormCover(getDefaultCoverName(["right-bottom","right-top"],1));
                break;
            case "bottom":
                // +3
                transFormCover(getDefaultCoverName(["bottom-left","bottom-right"],3));
                break;
            case "left":
                // -1
                transFormCover(getDefaultCoverName(["left-top","left-bottom"],-1));
                break;
        }

    }

    function initCover(element){
        element.find(".hf-clone").remove();
        var cover = element.find(".hf-cover:not(.hf-clone)");
        $.each(directionObj,function(k,v){
            var clone = cover.clone();
            var obj = {
                "transform-origin":k.replace("-"," "),
                "transform":v,
                "display":"block"
            };
            clone.removeAttr("cover-name").addClass("hf-clone").addClass(k).css(obj).appendTo(element);
        });
    }

    $(".hf-reversal .hf-item").bind({
        "pointerenter":function(e){
            var chromePointerEvents = typeof PointerEvent === 'function';
            if (chromePointerEvents) {
                if (e.pointerId === undefined) {
                    return;
                }
                // mouseleave不触发
                this.setPointerCapture(e.pointerId);
            }
            var item = $(this), cover = item.find(".hf-cover:not(.hf-clone)");
            initCover(item);
            item.mDirection(e,function(direction){
                transFormByDirection(cover,direction,true);
            })
        },
        "pointerleave":function(e){
            var that = this,item = $(this),cover = item.find(".hf-cover:not(.hf-clone)");
            $(this).mDirection(e,function(direction){
                transFormByDirection(cover,direction,false);
                var chromePointerEvents = typeof PointerEvent === 'function';
                if (chromePointerEvents) {
                    if (e.pointerId === undefined) {
                        return;
                    }
                    that.releasePointerCapture(e.pointerId);
                }
            })
        }
    })
}


/**
 * index.js
 */
var initNavItemClick = function(){
    $(".hf-nav li.hf-item").click(function(){
        var left = this.offsetLeft;
        var width = this.offsetWidth;
        $('.hf-nav span.li-bottom').animate({
            left: left + 'px',
            width:width+'px'
        }, "fast")
    })
}


/**
 * publish.html
 */

var initAceEdit = function(){
    ace.require("ace/ext/language_tools");
    var aceEdit = ace.edit('editor')
    aceEdit.setTheme('ace/theme/chrome');
    aceEdit.getSession().setMode('ace/mode/markdown');
    aceEdit.getSession().setUseWrapMode(false);
    aceEdit.renderer.setShowPrintMargin(false);
    aceEdit.$blockScrolling = Infinity;
    aceEdit.setReadOnly(false);
    aceEdit.setOptions({
        fontSize:'16px',
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: false
    });
    //设置高度
    aceEdit.container.style.lineHeight = 1.5
    aceEdit.renderer.updateFontSize();

    var avaHeight = $(window).height() - 170;
   // $(".custom-scroller").outerHeight(avaHeight);
    $('#stepEditor').height(avaHeight);
    $(window).resize(function () {
        var avaHeight = $(window).height() - 170;
       // $(".custom-scroller").outerHeight(avaHeight);
        $('#stepEditor').height(avaHeight);
    });

    return aceEdit;
}

var initMarked = function(){
    var aceEdit =  initAceEdit();
    marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true,
        tables: true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        highlight: function (code) {
            return hljs.highlightAuto(code).value;
        }
    });
    $('#previewBtn').on("click",function(){
        $("#preview").html(marked(aceEdit.getValue()));
    })
};

