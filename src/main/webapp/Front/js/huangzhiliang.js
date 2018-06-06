/**
 * Created by 25778 on 2018/5/18.
 */
// Easy Responsive Tabs Plugin
// Author: Samson.Onna <Email : samson3d@gmail.com>
(function ($) {
    $.fn.extend({
        easyResponsiveTabs: function (options) {
            //Set the default values, use comma to separate the settings, example:
            var defaults = {
                type: 'default', //default, vertical, accordion;
                width: 'auto',
                fit: true
            }
            //Variables
            var options = $.extend(defaults, options);
            var opt = options, jtype = opt.type, jfit = opt.fit, jwidth = opt.width, vtabs = 'vertical', accord = 'accordion';

            //Main function
            this.each(function () {
                var $respTabs = $(this);
                $respTabs.find('ul.resp-tabs-list li').addClass('resp-tab-item');
                $respTabs.css({
                    'display': 'block',
                    'width': jwidth
                });

                $respTabs.find('.resp-tabs-container > div').addClass('resp-tab-content');
                jtab_options();
                //Properties Function
                function jtab_options() {
                    if (jtype == vtabs) {
                        $respTabs.addClass('resp-vtabs');
                    }
                    if (jfit == true) {
                        $respTabs.css({ width: '100%', margin: '0px' });
                    }
                    if (jtype == accord) {
                        $respTabs.addClass('resp-easy-accordion');
                        $respTabs.find('.resp-tabs-list').css('display', 'none');
                    }
                }

                //Assigning the h2 markup
                var $tabItemh2;
                $respTabs.find('.resp-tab-content').before("<h2 class='resp-accordion' role='tab'><span class='resp-arrow'></span></h2>");

                var itemCount = 0;
                $respTabs.find('.resp-accordion').each(function () {
                    $tabItemh2 = $(this);
                    var innertext = $respTabs.find('.resp-tab-item:eq(' + itemCount + ')').text();
                    $respTabs.find('.resp-accordion:eq(' + itemCount + ')').append(innertext);
                    $tabItemh2.attr('aria-controls', 'tab_item-' + (itemCount));
                    itemCount++;
                });

                //Assigning the 'aria-controls' to Tab items
                var count = 0,
                    $tabContent;
                $respTabs.find('.resp-tab-item').each(function () {
                    $tabItem = $(this);
                    $tabItem.attr('aria-controls', 'tab_item-' + (count));
                    $tabItem.attr('role', 'tab');

                    //First active tab
                    $respTabs.find('.resp-tab-item').first().addClass('resp-tab-active');
                    $respTabs.find('.resp-accordion').first().addClass('resp-tab-active');
                    $respTabs.find('.resp-tab-content').first().addClass('resp-tab-content-active').attr('style', 'display:block');

                    //Assigning the 'aria-labelledby' attr to tab-content
                    var tabcount = 0;
                    $respTabs.find('.resp-tab-content').each(function () {
                        $tabContent = $(this);
                        $tabContent.attr('aria-labelledby', 'tab_item-' + (tabcount));
                        tabcount++;
                    });
                    count++;
                });

                //Tab Click action function
                $respTabs.find("[role=tab]").each(function () {
                    var $currentTab = $(this);
                    $currentTab.click(function () {

                        var $tabAria = $currentTab.attr('aria-controls');

                        if ($currentTab.hasClass('resp-accordion') && $currentTab.hasClass('resp-tab-active')) {
                            $respTabs.find('.resp-tab-content-active').slideUp('', function () { $(this).addClass('resp-accordion-closed'); });
                            $currentTab.removeClass('resp-tab-active');
                            return false;
                        }
                        if (!$currentTab.hasClass('resp-tab-active') && $currentTab.hasClass('resp-accordion')) {
                            $respTabs.find('.resp-tab-active').removeClass('resp-tab-active');
                            $respTabs.find('.resp-tab-content-active').slideUp().removeClass('resp-tab-content-active resp-accordion-closed');
                            $respTabs.find("[aria-controls=" + $tabAria + "]").addClass('resp-tab-active');

                            $respTabs.find('.resp-tab-content[aria-labelledby = ' + $tabAria + ']').slideDown().addClass('resp-tab-content-active');
                        } else {
                            $respTabs.find('.resp-tab-active').removeClass('resp-tab-active');
                            $respTabs.find('.resp-tab-content-active').removeAttr('style').removeClass('resp-tab-content-active').removeClass('resp-accordion-closed');
                            $respTabs.find("[aria-controls=" + $tabAria + "]").addClass('resp-tab-active');
                            $respTabs.find('.resp-tab-content[aria-labelledby = ' + $tabAria + ']').addClass('resp-tab-content-active').attr('style', 'display:block');
                        }
                    });
                    //Window resize function
                    $(window).resize(function () {
                        $respTabs.find('.resp-accordion-closed').removeAttr('style');
                    });
                });
            });
        }
    });
})(jQuery);

//新加的
(function() {
    var e=document,k=window,c={},s={},j=function(u){
        return u.constructor===Array
    },h={
        autoLoad:true,coreLib:["http://t.douban.com/js/jquery.min.js"],mods:{}
    },f=e.getElementsByTagName("script"),d=f[f.length-1],b,g,r=[],m=false,n=[],t=function(w,z,B,v,y){
        var u=f[0];if(!w){
            return}if(c[w]){
            s[w]=false;if(v){
                v(w,y)
            }return}if(s[w]){
            setTimeout(function(){t(w,z,B,v,y)},1);return
        }s[w]=true;var A,x=z||w.toLowerCase().substring(w.lastIndexOf(".")+1);if(x==="js"){
            A=e.createElement("script");A.setAttribute("type","text/javascript");A.setAttribute("src",w);A.setAttribute("async",true)}else{
            if(x==="css"){
                A=e.createElement("link");A.setAttribute("type","text/css");A.setAttribute("rel","stylesheet");A.setAttribute("href",w);c[w]=true
            }
        }if(B){
            A.charset=B
        }if(x==="css"){
            u.parentNode.insertBefore(A,u);if(v){
                v(w,y)
            }return
        }A.onload=A.onreadystatechange=function(){
            if(!this.readyState||this.readyState==="loaded"||this.readyState==="complete"){
                c[this.getAttribute("src")]=true;
                if(v){
                    v(this.getAttribute("src"),y)
                }A.onload=A.onreadystatechange=null
            }
        };u.parentNode.insertBefore(A,u)
    },q=function(B){
        if(!B||!j(B)){
            return
        }var x=0,A,v=[],z=h.mods,u=[],w={},y=function(F){
            var E=0,C,D;
            if(w[F]){
                return u
            }w[F]=true;if(z[F].requires){
                D=z[F].requires;
                for(;typeof(C=D[E++])!=="undefined";){
                    if(z[C]){y(C);u.push(C)}else{u.push(C)
                    }
                }return u
            }return u
        };
        for(;typeof(A=B[x++])!=="undefined";){
            if(z[A]&&z[A].requires&&z[A].requires[0]){
                u=[];w={};v=v.concat(y(A))
            }v.push(A)
        }return v
    },a=function(){
        m=true;
        if(r.length>0){
            g.apply(this,r);r=[]
        }
    },p=function(){
        if(e.addEventListener){
            e.removeEventListener("DOMContentLoaded",p,false)
        }else{
            if(e.attachEvent){
                e.detachEvent("onreadystatechange",p)
            }
        }a()
    },o=function(){
        if(m){
            return
        }try {
            e.documentElement.doScroll("left")
        }catch(u){
            return k.setTimeout(o,1)}a()
    },i=function(){
        if(e.readyState==="complete"){
            return k.setTimeout(a,1)
        }var u=false;
        if(e.addEventListener){
            e.addEventListener("DOMContentLoaded",p,false);
            k.addEventListener("load",a,false)}else{
            if(e.attachEvent){
                e.attachEvent("onreadystatechange",p);
                k.attachEvent("onload",a);
                try{
                    u=(k.frameElement===null)
                }catch(v){}if(document.documentElement.doScroll&&u){o()
                }
            }
        }
    },l=function(u){
        if(!u||!j(u)){
        return
    }this.queue=u;this.current=null
    };
    l.prototype={
        _interval:10,start:function(){
            var u=this;this.current=this.next();
            if(!this.current){this.end=true;return}this.run()
        },run:function(){
            var w=this,u,v=this.current;
            if(typeof v==="function"){
                v();
                this.start();
                return}else{
                if(typeof v==="string"){
                    if(h.mods[v]){
                        u=h.mods[v];
                        t(u.path,u.type,u.charset,function(x){w.start()
                        },w)
                    }else{
                        if(/\.js|\.css/i.test(v)){
                            t(v,"","",function(x,y){
                                y.start()
                            },w)
                        }else{this.start()}
                    }
                }
            }
        },next:function(){
            return this.queue.shift()
        }
    };b=d.getAttribute("data-cfg-autoload");if(typeof b==="string"){
        h.autoLoad=(b.toLowerCase()==="true")?true:false}b=d.getAttribute("data-cfg-corelib");
    if(typeof b==="string"){
        h.coreLib=b.split(",")
    }g=function(){var v=[].slice.call(arguments),u,w;
        for(w=0;w<v.length;w++){
            if(typeof v[w]=="object"){
                g.add(v[w].path,v[w]);v[w]=v[w].path
            }
        }if(n.length>0){
            v=n.concat(v)
        }if(h.autoLoad){
            v=h.coreLib.concat(v)
        }u=new l(q(v));
        u.start()
    };g.add=function(v,u){
        if(!v||!u||!u.path){
            return
        }h.mods[v]=u
    };g.delay=function(){
        var v=[].slice.call(arguments),u=v.shift();
        k.setTimeout(function(){
            g.apply(this,v)},u)
        ;g.global=function(){
            var u=[].slice.call(arguments);
        }
        n=n.concat(u)
    };g.ready=function() {
        var u=[].slice.call(arguments);
        if(m){
            return g.apply(this,u)}r=r.concat(u)
    };g.css=function(v){
        var u=e.getElementById("do-inline-css");
        if(!u){
            u=e.createElement("style");
            u.type="text/css";
            u.id="do-inline-css";
            f[0].parentNode.insertBefore(u,f[0])
        }if(u.styleSheet){
            u.styleSheet.cssText=u.styleSheet.cssText+v
        }else{
            u.appendChild(e.createTextNode(v))
        }
    };
    if(h.autoLoad){
        g(h.coreLib)}this.Do=g;i()
})();
//新加的
Do.add("mousewheel",{
    path:LH.res_path("/jslib/jquery-mousewheel-master/jquery.mousewheel.js"),type:"js"
});
Do.add("nicescroll",{
    path:LH.res_path("/jslib/jquery.nicescroll.300RC4/jquery.nicescroll.min.js"),type:"js"
});
Do.add("masonry",{
    path:LH.res_path("jslib/masonry-site/jquery.masonry.js"),type:"js"
});
Do.add("tpls",{
    path:LH.res_path("jslib/artTemplate/template.min.js"),type:"js"
});
Do.add("jcrop",{
    path:LH.res_path("jslib/jcrop/js/jquery.Jcrop.js"),type:"js"
});
Do.add("address",{
    path:LH.res_path("jslib/jquery.address-1.5/jquery.address-1.5.js"),type:"js"
});
Do.add("viewport",
    {path:LH.res_path("jslib/viewport/js/jquery.viewport.js"),type:"js"
    });
Do.add("json",{
    path:LH.res_path("jslib/jquery.json/src/jquery.json.js"),type:"js"
});
Do.add("cookie",{
    path:LH.res_path("jslib/carhartl-jquery-cookie/jquery.cookie.js"),type:"js"
});
Do.add("easing",{
    path:LH.res_path("jslib/jquery.easing/jquery.easing.1.3.js"),type:"js"
});
Do.add("_mailautocomplete",{
    path:LH.res_path("jslib/mailAutoComplete/jquery.mailAutoComplete.css"),type:"css"
});
Do.add("mailautocomplete",{
    path:LH.res_path("jslib/mailAutoComplete/jquery.mailAutoComplete.js"),type:"js",requires:["_mailautocomplete"]
});
Do.add("svgmap",{
    path:LH.res_path("jslib/china-map/raphael.js"),type:"js"
});
Do.add("svgmapdata",{
    path:LH.res_path("jslib/china-map/chinamapPath.js"),type:"js",requires:["svgmap"]
});
Do.add("ajaxupload",{
    path:LH.res_path("jslib/jquery/jquery.ajaxupload.js"),type:"js"
});
Do.add("drag",{
    path:LH.res_path("jslib/jquery/jquery.drag.js"),type:"js"
});
Do.add("topmsg",{
    path:LH.res_path("jslib/jquery/jquery.topmsg.js"),type:"js"
});
Do.add("browsersize",
    {path:LH.res_path("jslib/jquery/jquery.browsersize.js"),type:"js"
    });
Do.add("lybox",{
    path:LH.res_path("jslib/jquery/jquery.lybox.js"),type:"js",requires:["browsersize","drag"]
});
Do.add("dialog",{
    path:LH.res_path("js/sl/dialog.js"),type:"js",requires:["lybox"]
});
Do.add("slideShow",{
    path:LH.res_path("js/web/jquery.slideShow.js"),type:"js"
});
Do.add("optometry",{
    path:LH.res_path("js/web/optometry.js"),type:"js"
});
Do.add("load_img",{
    path:LH.res_path("js/sl/load_img.js"),type:"js"
});
Do.add("mCustomScrollbar", {
    path:LH.res_path("js/web/jquery.mCustomScrollbar.js"),type:"js"
});
Do.add("textSlide",{
    path:LH.res_path("jslib/textSlide.js"),type:"js"
});
Do.add("dragable",{
    path:LH.res_path("jslib/jquery.drag.js"),type:"js"
});
Do.add("popBox",{
    path:LH.res_path("jslib/jquery.popBox.js"),type:"js",requires:["dragable"]
});
Do.add("waterFall",{
    path:LH.res_path("js/web/jquery.waterfall.js"),type:"js"
});
Do.add("appoints",{
    path:LH.res_path("js/web/appoints.js"),type:"js"
});
Do.add("easing",{
    path:LH.res_path("jslib/2015_new-turntable/jquery.easing.min.js"),type:"js"
});
Do.add("turntable",{
    path:LH.res_path("jslib/2015_new-turntable/jQueryRotate.2.2.js"),type:"js",requires:["easing"]
});
//新加的
var LH={
    ie6:!-[1,]&&!window.XMLHttpRequest,hostname:window.location.hostname,syncd:["loho88.com","loho88.cn"],cnArr:["lohossid","username"],updateUrl:function(){
        var a="http://"+LH.hostname;LH.url=LH.debug?{web:a+"/",mob:a+":8080/m-loho88/",user:a+":8080/user/",order:a+":8081/order/",cdn:"http://image.loho88.com/"
        }:{
            web:"http://www.loho88.com/",mob:"http://m.loho88.com/",user:"http://user.loho88.com/",order:"http://order.loho88.com/",cdn:"http://image.loho88.com/"
        }
    },setDebug:function(a){
        LH.debug=!!a;LH.updateUrl()},fitScr:function(){
        if($(window).width()<1200){
            $("body").addClass("w")
        }else{$("body").removeClass("w")
        }
    },loadImg:function(e,d,b){
        if(!arguments.length){
            arguments[0]="data-src"
        }var c=$(window);var j=this.isIpad();
        var g=j?(window.screen.height)+200:c.height();
        for(var h=0,f=arguments.length;h<f;h++){
            var a=arguments[h];if(typeof(a)=="string"){
                $("img["+a+"]").each(function(){
                    var k=c.scrollTop();
                    var l=$(this),m=l.offset().top,i=40;
                    if(m+l.height()+i>=k&&m<=k+g+i&&l.is(":hidden")==false){
                        l.attr("src",l.attr(a)).removeAttr(a)
                    }
                })
            }
        }
    },isIpad:function(){
        var a=navigator.userAgent.toLowerCase();
        if(a.match(/iPad/i)=="ipad"){
            return true}else{return false
        }
    },setCookie:function(b,e,a,h,d,g){
        var f=new Date();
        f.setDate(f.getDate()+a);
        var c=document.cookie=b+"="+escape(e)+((a?";expires="+f.toGMTString():"")+(h?"; path="+h:"")+(d?"; domain="+d:"")+(g?"; secure="+g:""))
    },getCookie:function(b){
        var a=document.cookie;
        if(a.length){c_start=a.indexOf(b+"=");
            if(c_start!=-1){
                c_start=c_start+b.length+1;
                c_end=a.indexOf(";",c_start);
                if(c_end==-1){
                    c_end=a.length
                }return unescape(a.substring(c_start,c_end))
            }
        }return""
    },getUrlPara:function(a){
        var b=new RegExp("(^|\\?|&)"+a+"=([^&]*)(\\s|&|$)","i");
        if(b.test(location.href)){
            return unescape(RegExp.$2.replace(/\+/g," "))
        }return""
    },verify:function(c,a){
        var b={
            mob:/^1[0-9]\d{9}$/,tel:/0\d{2,3}-\d{5,9}|0\d{2,3}-\d{5,9}/,date:/(\d{4})[/-](\d{1,2})[/-](\d{1,2})/,mail:/\w+\@\w+\.\w+/};if(b[a]){
            return b[a].test(c)
        }
    },errorMark:function(b,a){
        b.css({
            "border-color":"#f00",position:"relative",top:0
        });
        b.animate({
            top:-5
        },80).animate({
            top:4
        },100).animate({
            top:-3
        },100).animate({
            top:2},100).animate({
            top:0
        },80,function(){
            b.removeAttr("style");
            if(a!==false){b.focus()
            }
        })
    },ajaxError:function(a){
        var b={
            400:"错误的请求",404:"服务器找不到请求的链接",500:"服务器内部错误",503:"服务暂不可用",504:"网关超时"
        };
        return b[a]||"未知的请求错误"
    },secondCountdown:function(b,a,d){b.text(a);
        var c=window.setInterval(function(){
            if(a>0){
            a--;b.text(a)
        }else{
            window.clearInterval(c);
            if(typeof(d)=="function"){
                d()
            }
        }
        },1000)
    },getLocalTime:function(b){
        var c=new Date(parseInt(b)*1000),d=c.getMinutes(),a=c.getSeconds();
        if(d<10){d="0"+d}if(a<10){
            a="0"+a
        }return c.getFullYear()+"年"+(c.getMonth()+1)+"月"+c.getDate()+"日 "+c.getHours()+":"+d+":"+a},_ck:function(c){
        var f=this.hostname.substring(this.hostname.indexOf(".")+1);
        for(var b in this.syncd){
            if(this.syncd[b]!=f){
                var e=document.createElement("script");
                e.src="http://www."+this.syncd[b]+"/sync.php?k=bcl2";
                c=c?c:this.cnArr;for(var a in c){
                    if(""!=this.getCookie(c[a])){
                        e.src+="&"+c[a]+"="+this.getCookie(c[a])
                    }
                }document.body.appendChild(e)
            }
        }
    }
};
LH.userName=LH.getCookie("username");
LH.getCartNum=function(b){
    var a=$.Deferred();
    $.ajax({
        type:"GET",url:"/cart/listCartNum/",dataType:"json",success:function(d){
            try{
                if(d&&d.status){
                    a.resolve(d.cartNum)}else{
                    a.reject(d.cartNum)}}catch(c){
                console.log("购物车数据获取失败"+c)
            }
        }
    });
    return a.promise()
};
(function(){
    var a=LH.hostname;LH.setDebug(a.indexOf("192.168.1.")>-1||a.indexOf("127.0.0.")>-1||a=="localhost")
})();
LH.res_path=function(d,c){
    var a=LH,b=a.res_dir.substr(-1,1)=="/"?a.res_dir:(a.res_dir+"/");
    d=b+(d.substr(0,1)=="/"?d.substr(1):d);
    if(c!==false&&c!==0){
        if(/\?/.test(d)){
            d+="&"+LH.res_ver
        }else{d+="?"+LH.res_ver
        }
    }return d
};
LH.trace=function(){
    this.debug&&console&&console.log&&console.log.apply(this,arguments)
};
