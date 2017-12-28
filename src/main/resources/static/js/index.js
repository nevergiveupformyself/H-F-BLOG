if (typeof jQuery === 'undefined') {
    throw new Error('Bootstrap\'s JavaScript requires jQuery')
}
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

    $(".hf-item").hover(function(e){
        var item = $(this), cover = item.find(".hf-cover:not(.hf-clone)");
        initCover(item);
        item.mDirection(e,function(direction){
            transFormByDirection(cover,direction,true);
        })
    },function(e){
        var item = $(this);
        var cover = item.find(".hf-cover:not(.hf-clone)");
        $(this).mDirection(e,function(direction){
            transFormByDirection(cover,direction,false);
        })
    })
}
