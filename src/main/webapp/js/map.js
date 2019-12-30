$(function(){
		 //zeroModal.alert("鼠标单击地图可获得经纬度！"); 
		 //setTimeout(function(){popup({type:'tip',msg:"登录超时！",delay:null});location="user/logout"},1800000); 
    	 //坐标反查
    	 var flag=$("input[type='checkbox']").is(':checked');
    	 $("#lat-lng").hide(); 
    	 $("#Checkbox").click(function(){
    		 $("#ct").toggle();
    		 $("#lat-lng").toggle(); 
    	 });
    	 
    	 function addClickHandler(target, window) {
    	        target.addEventListener("click", function() {
    	            target.openInfoWindow(window);
    	        });
    	    } 
    	 
    	 var map = new BMap.Map("container");        //在container容器中创建一个地图,参数container为div的id属性;
         var point = new BMap.Point(117.236603,31.828296);    //创建点坐标
         var geolocation = new BMap.Geolocation();
         geolocation.getCurrentPosition(function(r) {
             // 定位成功事件
             if(this.getStatus() == BMAP_STATUS_SUCCESS) {
            	 zeroModal.alert('您的位置：' + r.point.lng + ',' + r.point.lat);
                 point = new BMap.Point(r.point.lng, +r.point.lat);
             }
         }, {
             enableHighAccuracy: true
         });
         map.centerAndZoom(point, 14);
         map.centerAndZoom(point, 12);                //初始化地图，设置中心点坐标和地图级别  地图缩放比例
         map.enableScrollWheelZoom();                //激活滚轮调整大小功能
         map.addControl(new BMap.NavigationControl());    //添加控件：缩放地图的控件，默认在左上角；
         map.addControl(new BMap.MapTypeControl());        //添加控件：地图类型控件，默认在右上方；
         map.addControl(new BMap.ScaleControl());        //添加控件：地图显示比例的控件，默认在左下方；
         map.addControl(new BMap.OverviewMapControl());  //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl 
         	var search = new BMap.LocalSearch("中国", {
          	 onSearchComplete: function(result){
             if (search.getStatus() == BMAP_STATUS_SUCCESS){
                   var res = result.getPoi(0);
                   var point = res.point;
                   map.centerAndZoom(point, 11);
             }

           },renderOptions: {  //结果呈现设置，
             map: map,    
             autoViewport: true,  
             selectFirstResult: true 
           } ,onInfoHtmlSet:function(poi,html){
             // alert(html.innerHTML)
           }
         });
         	var geolocation = new BMap.Geolocation();
            geolocation.getCurrentPosition(function(r) {
                // 定位成功事件
                if(this.getStatus() == BMAP_STATUS_SUCCESS) {
                    /*alert('您的位置：' + r.point.lng + ',' + r.point.lat);*/ 
                    point = new BMap.Point(r.point.lng, +r.point.lat); 
                }
            }, {
                enableHighAccuracy: true
            });
            map.centerAndZoom(point, 14);
         //设置城市的函数
         $("#go").click(function(){ 
        	 var city1=$("#cityName").val(); 
             search.search(city1); //显示 
            var local = new BMap.LocalSearch(map, {
                renderOptions:{map: map}
            }); 
	        local.search(city1);
         });
      // 根据坐标得到地址描述  
	      $("#go-lat").click(function(){
	    	 var myGeo = new BMap.Geocoder(); 
	    	 var lng=$("#lng").val();
	 	     var lat=$("#lat").val();
	 	     var point1=new BMap.Point(lng,lat); //经度,纬度   
	    	  myGeo.getLocation(point1, function(result){       
			      if (result){      
			    	  var ct=result.address;
			          search.search(ct);
		          }  
		      }); 
	      }); 
         //ip定位 当前城市
          function myFun(result){
        		var cityName = result.name;
        		map.setCenter();
        		//初始化显示的城市   
            	search.search(cityName);   
        	}
        	var myCity = new BMap.LocalCity();
        	myCity.get(myFun);
         //点击事件获取经纬度
         map.addEventListener("click", function(e) { 
        	 zeroModal.success("鼠标单击位置的经纬度为："+ e.point.lng + "," + e.point.lat);
         });
    });