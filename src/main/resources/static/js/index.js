/**
* search.js
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

function showEffect(name){
    //showCss
    $("#search .active").removeClass("active").hide();
    $("#search #"+name).addClass("active").show();
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
    aceEdit.getSession().setUseWrapMode(true);
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


function handleMarkDownIcon(editor,syntaxObj){

    var syntax = syntaxObj.syntax ||"",
        prevLine = syntaxObj.prevLine,
        nextLine = syntaxObj.nextLine,
        type = syntaxObj.type;
    var value = editor.session.getTextRange(editor.getSelectionRange()),
        range = editor.getSelectionRange(),
        start = range.start,
        end = range.end,
        length = editor.session.getLine(end.row).length,
        lines = value.split("\n"),syntaxLines=[];


    if(syntax instanceof Array){
        if(lines.length<=1){
            syntax = syntax[0];
        }else{
            syntax = syntax[1];
            if(start.column){
                syntax = "\n"+ syntax ;
            }
        }
    }

   if(prevLine){


       $.each(lines,function(i,value){
           if(type == "number"){
               syntax = i+1+". %s";
           }
           syntaxLines.push(sprintf(syntax,value)+ (nextLine?"\n":""));
       });
       syntax = syntaxLines.join("\n");

        if(start.column){
            syntax = "\n"+ syntax ;
        }
        if(end.column != length && value){
            syntax = syntax + "\n";
        }

    }else if(!value){
        range.end.column = length;
        value = editor.session.getTextRange(range);
        syntax = sprintf(syntax,value);
    }else{
        syntax = sprintf(syntax,value);
    }

    editor.insert(syntax);
    editor.navigateLineEnd();
    editor.focus();
}
var aceEdit = null;
var initPublish = function(){
    aceEdit = initAceEdit();
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
        editor.focus();
    });

    $(".hf-select").hfSelect({
        multiple:true,
        size:4,
        inputCss:{
            width:"100%"
        },
        autoWidth:true
    })

    var markdownSyntaxObj = {
        h1:{
            syntax:  "# %s",
            prevLine:true
        },
        h2:{
            syntax: "## %s",
            prevLine:true
        },
        h3:{
            syntax:"### %s",
            prevLine:true
        },
        h4:{
            syntax:"#### %s",
            prevLine:true
        },
        "list-ol":{
            type:"number",
            prevLine:true
        },
        "list-ul":{
            syntax:"* %s",
            prevLine:true
        },
        separate:{
            syntax:"\n---\n%s",
            prevLine:true
        },
        "quote-right":{
            syntax:"> %s",
            prevLine:true,
            nextLine:true
        },
        bold:{
            syntax:"**%s**",
        },
        italic:{
            syntax:"*%s*",
        },
        strikethrough:{
            syntax:"~~%s~~",
        },
        code:{
            syntax:[
                "`%s`",
                "```\n%s\n```\n"
            ]
        },

    };

    $("#toolbar .editor-icon").on("click",function(){
        var name = $(this).attr("name"),
            markdownSyntax = markdownSyntaxObj[name];
        if(markdownSyntax){
            handleMarkDownIcon(aceEdit,markdownSyntax);
        }
    })


};
var publishArticle = function (){
    var title = $("#articleTitle>input").val(),
        tags = [],
        value = aceEdit.getValue();

    $("#articleTag .hf-select span.hf-choices>span.hf-choice").each(function(i,v){
        tags.push($(this).val());
    });
    $.ajax({
        type:"POST",
        data:{
            articleName:title,
            articleContent:value
        },
        url:"/article/publish",
        success:function(){
            alert("保存成功")
        }
    })
};

$(".return-top").on("click",function(){
    $("body,html").animate({scrollTop:0});
});

$(window).on("scroll",function(){
    var scrollTop = $(window).scrollTop();
    if(scrollTop>0){
        $(".return-top").fadeIn();
    }else{
        $(".return-top").fadeOut();
    }
});

$('.hf-nav .hf-item').click(function (e) {


    if($(this).text().trim() != "首页"){
       $(".hf-header .head-animate").show().stop().animate({
           "width":"55%"
       });
    }else{
        $(".hf-header .head-animate").hide().css({
            "width":"0"
        });
    }
});
