if (typeof jQuery === 'undefined') {
    throw new Error('mDirection JavaScript requires jQuery')
}
+function ($) {
    'use strict';

    var Direction = function(element){
        this.$element = element;
    };

    Direction.DEFAULTS = {

    };

    Direction.prototype.init = function (enterObj,leaveObj) {
        var $this = this
        var $el = this.$element
        $el.addEventListener('mouseenter',function(e){
            var directionNumber = $this.getDirectionNumber(e);
            var funArray = [enterObj.top,enterObj.right,enterObj.bottom,enterObj.left];
            funArray[directionNumber]($el);
        },false);

        $el.addEventListener('mouseleave',function(e){
            var directionNumber = self.main(e);
            var funArray = [leaveObj.top,leaveObj.right,leaveObj.bottom,leaveObj.left];
            funArray[directionNumber]($el);
        },false);
    };

    Direction.prototype.getDirectionNumber = function(e){
        var $el = this.$element
        var width = $el.width()
        var height = $el.height()

        // 获得相对于中心点的 x，y坐标
        var x = (e.pageX - $el.offset().left-( width / 2 )) * (width >height ? (height / width ):1);
        var y = (e.pageY- $el.offset().top -( height / 2 )) * (height >width ? (width / height):1);
        // Math.atan2(y,x) 获得斜率 -PI 到PI之间的数
        var number = Math.round((((Math.atan2(y, x) * 180 / Math.PI) + 180) / 90) + 3) % 4;
        return number;
    }

    Direction.prototype.getDirection = function(e){
        var directionNumber = this.getDirectionNumber(e);
        var direction = "";
        switch (directionNumber)
        {
            case 0 :
                direction =  "top";
                break;
            case 1:
                direction = "right";
                break;
            case 2:
                direction =  "bottom";
                break;
            case 3:
                direction =  "left";
                break;
        }
        return direction;
    }


    $.fn.mDirection   = function(e,callback){
        var $element = this;
        var mDirection = new Direction($element);
        if(callback){
            callback.call($element,mDirection.getDirection(e));
        }else{
            return mDirection.getDirection(e);
        }
    };
    
    $.fn.mDirection.Constructor =  function(element,enterObj,leaveObj){
        var $element = $(element);
        var mDirection = new Direction($element);
        mDirection.init(enterObj,leaveObj)
    }


}(jQuery);
