<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/common_head.jsp"/>
<header>
<jsp:include page="../common/navbar.jsp"/>
</header>
      <div class="container">    
          <div class="jumbotron">
            <div class="row">
                <div class="col-md-3 col-xs-12 col-sm-6 col-lg-3">
                  <div class="thumbnail text-center photo_view_postion_b" >
                    <img id="profileBtn" src="${img}/song.jpg" alt="stack photo" class="img">
                  </div>
                </div>
                <div class="col-md-9 col-xs-12 col-sm-6 col-lg-9">
                    <div class="" style="border-bottom:1px solid black">
                      <span style="font-size: 50px">${requestScope.student.name}</span>
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <button id="updateBtn" onclick="moveTo('member','member_update')">정보수정</button>
                    </div>
                      <hr>
                    <div class="col-md-6">  
                    <ul class=" details"> 
                      <li><p><span class="glyphicon glyphicon-credit-card one" style="width:50px;"></span>${requestScope.student.id}</p></li>
                      <li><p><span class="glyphicon glyphicon-bold" style="width:50px;"></span>${requestScope.student.ssn}</p></li>
                      <li><p id="detail_phone"><span  class="glyphicon glyphicon-earphone one" style="width:50px;"></span>${requestScope.student.phone}</p></li></ul>
                    </div>
                    <div class="col-md-6">  
                      <p><span class="glyphicon glyphicon-calendar one" style="width:50px;"></span>${requestScope.student.regdate}</p>
                      <p id="detail_title"><span  class="glyphicon glyphicon-blackboard" style="width:50px;"></span>${requestScope.student.subjects}</p>
                      <p id="detail_email"><span class="glyphicon glyphicon-envelope one" style="width:50px;"></span>${requestScope.student.email}</p>
                      <p id="detail_id" style="visibility:hidden;"><span class="glyphicon glyphicon-envelope one" style="width:50px;"></span>${requestScope.student.id}</p>
                    </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group row">
                  <div class="col-md-12">
                  <div class="form-group" style="border-bottom:1px solid black">
                       <h2><span class="glyphicon glyphicon-list-alt one" style="width:50px;"></span>성적 목록</h2>
                  </div>
                  </div>
                </div>
              </div>
              <div class="row"> 
               <div class="col-md-4">
                  <div class="col-sm-6 col-xs-6 tital " >Height(feet):</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Weight(lbs):</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Hair Color:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Hair Length:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Suit/Dress:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
               </div>
               <div class="col-md-4">
                  <div class="col-sm-6 col-xs-6 tital " >Shirt Size:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Shoe Size:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Bust:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Waist:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Inseam:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
               </div>
               <div class="col-md-4">
                  <div class="col-sm-6 col-xs-6 tital " >Hips:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Glove:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
                  <div class="col-sm-6 col-xs-6 tital " >Hat:</div><div class="col-sm-6 col-xs-6 contant_i">Prasad</div>
                  <div class="clearfix"></div><div class="bot-border"></div>
               </div>
              </div>
              
          </div>
      </div>
<script>
memberDetail.init();

/* var str = $("p:fitst").text();
$("p:last").html(str);
 */
</script>
      
      
      
      
      
      
      
      
      
      