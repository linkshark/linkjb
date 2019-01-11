function $id(id) {
    return document.getElementById(id);
}

function bindEvent() {
    var sea = $id("my_search");
    /*banner对象*/
    var banner = $id("my_banner");
    window.onscroll = function() {
        var height = banner.offsetHeight;//高度
        var top = document.body.scrollTop;//卷上去的高度
        /*当滚动高度大于banner的高度时候颜色不变*/
        if (top > height) {
            sea.style.background = "rgba(201,21,35,0.85)";
        } else {
            /*从上往下滚，banner从透明到0.85*/
            var op = top / height * 0.85;
            sea.style.background = "rgba(201,21,35," + op + ")";
        }
    };
}

function scrollPic() {
    var imgBox = document.getElementsByClassName("banner_box")[0]; //ul
    var banner = $id("my_banner");//div
    var pointBox = document.getElementsByClassName("point_box")[0];//point
    var ols = [...pointBox.children];
    var indexx = 0;
    var timer = null;
    var moveX = 0;
    var endX = 0;
    var startX = 0;

    function addTransition() {
        imgBox.style.transition = "all .3s ease 0s";//all 所有属性改变  .3s元素 转换过程的持续时间 ease 变换速率 0s一个动画开始执行的时间
        imgBox.style.webkitTransition = "all .3s ease 0s";
    }

    function removeTransition() {
        imgBox.style.transition = "none";
        imgBox.style.webkitTransition = "none";
    }

    //定义转换，只是用 X 轴的值---定义起始位置
    function setTransfrom(t) {
        imgBox.style.transform = `translateX(${t}px)`;
    }

    function runTask(){
        ++indexx;  //1
        addTransition();

        // 小方块
        if (indexx >= ols.length) {
            indexx = 0;
        }
        setTransfrom(-indexx * banner.offsetWidth);  //-1*400
        ols.forEach(d => d.className = '');
        /*ols.forEach(function(d,i,item){
            console.log(d);
            d.className = ''
        })*/
        //console.log(indexx)

        for (var i = 0; i <ols.length; i++) {
          ols[i].index = i;
          (function(i) {
            ols[i].onclick = function() {
              for(var j = 0 ;j < ols.length; j++){
                ols[j].className = "";
              }
              indexx=this.index;
              console.log(i);
              setTransfrom(-indexx*banner.offsetWidth);
              ols[indexx].className = "white";
            }
          })(i)

        }
      ols[indexx].className = "now";
    }

    //3. 开始动画部分
    pointBox.children[0].className = "now";  //轮播点添加样式
    timer = setInterval(runTask, 2000);
    /**
     * 触摸事件开始
     https://www.cnblogs.com/laneyfu/p/6156409.html
     touchstart事件：当手指触摸屏幕时候触发，即使已经有一个手指放在屏幕上也会触发。
     */
    imgBox.addEventListener("touchstart", function(e) {
        console.log("开始");
        var event = e || window.event;
        //记录开始滑动的位置
        startX = event.touches[0].clientX;
        console.log(startX);
    });
    /**
     * 触摸滑动事件
     当手指在屏幕上滑动的时候连续地触发。在这个事件发生期间，调用preventDefault()事件可以阻止滚动
     */
    imgBox.addEventListener("touchmove", function(e) {
        console.log("move");
        var event = e || window.event;
        event.preventDefault();

        //清除定时器
        clearInterval(timer);
        //记录结束位置
        endX = event.touches[0].clientX;
        //记录移动的位置
        moveX = startX - endX;
        //alert(moveX);
        removeTransition();
        setTransfrom(-indexx * banner.offsetWidth - moveX);
    });
    /**
     * 触摸结束事件
     当手指从屏幕上离开的时候触发
     */
    imgBox.addEventListener("touchend", function() {
        console.log("end");
        //如果移动的位置大于三分之一，并且是移动过的
        if (Math.abs(moveX) > (1 / 3 * banner.offsetWidth) && endX != 0) {
            // alert(indexx);
            if(indexx >= ols.length-1) {
                indexx=0;
            }else if(indexx <= 0){
                indexx= ols.length-1;
            }else {
                //向左
                if (moveX > 0) {
                    indexx++;
                } else {
                    indexx--;
                }
            }
            ols.forEach(d => d.className = '');
            ols[indexx].className = "now";
            //改变位置
            setTransfrom(-indexx * banner.offsetWidth);
        }
        //回到原来的位置
        addTransition();
        setTransfrom(-indexx * banner.offsetWidth);
        //初始化
        startX = 0;
        endX = 0;
        clearInterval(timer);
        timer = setInterval(runTask, 2000);
    });
};

module.exports = {
    bindEvent,
    scrollPic
}
