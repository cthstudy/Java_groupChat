//用户全选
	$("#SelectAllUser").click(function(){
		if ($("#SelectAllUser").is(":checked")) {
			 $("input[name='checkUser']").prop("checked", true);//所有选择框都选中
        } else {
            $("input[name='checkUser']").prop("checked", false);
        }
	})
	//批量删除用户
	$(".someDelUser").click(function(){
		//判断至少写了一项
		var flag=false;
        var checkedNum = $("input[name='checkUser']:checked").length;
        if (checkedNum == 0) {
        	zeroModal.alert("请至少选择一个!"); 
        	return false;
        }
        var checkedList = new Array();
        $("input[name='checkUser']:checked").each(function() {
            checkedList.push($(this).parent().parent().find("td").eq(0).text());//id 的序号 集合
        });
        $.ajax({
            type : "POST",
            url : 'device/someDelUser',
            data : {
                "idList" : checkedList.toString()
            },
            success : function(data) {
            	zeroModal.success({ 
                    content: "删除成功！!",  
                    contentDetail: '数据已刷新！', 
                    okFn: function() {
                    	var userName=localStorage.getItem("userName");
                		var passWord=localStorage.getItem("passWord");
                    	//var imei=localStorage.getItem("imei");
                    	location="device/toUserInfo?userName="+userName+"&passWord="+passWord; 
                    }
                });
            }
        });
	})
	
	//设备全选
	$("#SelectAllDevice").click(function(){
		if ($("#SelectAllDevice").is(":checked")) {
			 $("input[name='checkDeivce']").prop("checked", true);//所有选择框都选中
        } else {
            $("input[name='checkDeivce']").prop("checked", false);
        }
	})
	//批量删除设备
	$(".someDelDeivce").click(function(){
		//判断至少写了一项
		var flag=false;
        var checkedNum = $("input[name='checkDeivce']:checked").length;
        if (checkedNum == 0) {
        	zeroModal.alert("请至少选择一个!"); 
        	return false;
        }
        var checkedList = new Array();
        $("input[name='checkDeivce']:checked").each(function() {
            checkedList.push($(this).parent().parent().find("td").eq(0).text());//id 的序号 集合
        });
        $.ajax({
            type : "POST",
            url : 'device/someDelDevice',
            data : {
                "idList" : checkedList.toString()
            },
            success : function(data) {
            	zeroModal.success({ 
                    content: "删除成功！!",  
                    contentDetail: '数据已刷新！', 
                    okFn: function() {
                    	//var imei=localStorage.getItem("imei");
                    	location="device/toConsole"; 
                    }
                });
            }
        });
	})
	
	//短信全选
	$("#SelectAllSms").click(function(){
		if ($("#SelectAllSms").is(":checked")) {
			 $("input[name='checkSms']").prop("checked", true);//所有选择框都选中
        } else {
            $("input[name='checkSms']").prop("checked", false);
        }
	})
	//批量删除短信
	$(".someDelSms").click(function(){
		//判断至少写了一项
		var flag=false;
        var checkedNum = $("input[name='checkSms']:checked").length;
        if (checkedNum == 0) {
        	zeroModal.alert("请至少选择一个!"); 
        	return false;
        }
        var checkedList = new Array();
        $("input[name='checkSms']:checked").each(function() {
            checkedList.push($(this).parent().parent().find("td").eq(0).text());//id 的序号 集合
        });
        $.ajax({
            type : "POST",
            url : 'device/someDelSms',
            data : {
                "idList" : checkedList.toString()
            },
            success : function(data) {
            	zeroModal.success({ 
                    content: "删除成功！!",  
                    contentDetail: '数据已刷新！', 
                    okFn: function() {
                    	var imei=localStorage.getItem("imei");
                    	location="device/toSMSInfo?imei="+imei;   
                    }
                });
            }
        });
	})
	
	//通讯录全选
	$("#SelectAllBook").click(function(){
		if ($("#SelectAllBook").is(":checked")) {
			 $("input[name='checkBook']").prop("checked", true);//所有选择框都选中
        } else {
            $("input[name='checkBook']").prop("checked", false);
        }
	})
	//批量删除通讯录
	$(".someDelBook").click(function(){
		//判断至少写了一项
		var flag=false;
        var checkedNum = $("input[name='checkBook']:checked").length;
        if (checkedNum == 0) {
        	zeroModal.alert("请至少选择一个!"); 
        	return false;
        }
        var checkedList = new Array();
        $("input[name='checkBook']:checked").each(function() {
            checkedList.push($(this).parent().parent().find("td").eq(0).text());//id 的序号 集合
        });
        $.ajax({
            type : "POST",
            url : 'device/someDelBook',
            data : {
                "idList" : checkedList.toString()
            },
            success : function(data) {
            	zeroModal.success({ 
                    content: "删除成功！!",  
                    contentDetail: '数据已刷新！', 
                    okFn: function() {
                    	var imei=localStorage.getItem("imei");
                    	location="device/addressBook?imei="+imei; 
                    }
                });
            }
        });
	})