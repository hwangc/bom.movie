<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

<!-- Theme CSS -->
<link href="vendor/agency/agency.min.css" rel="stylesheet">
<link href="css/bom.css" rel="stylesheet">

<title>Register</title>
</head>
    <!-- register Section -->
    <section id="register">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">봄 회원가입</h2>
                    <h3 class="section-subheading text-muted">당신도 모르던 당신의 영화취향을 알려드립니다.</h3>
                </div>
            </div>
            <div class="row">
              <div class="col-xs-3 text-center"></div>
              <div class="col-xs-6 text-center">
                    <form name="sentMessage" id="registerForm" novalidate>
                        <div class="row">
                            <div class="text-center">
                                <div class="form-group text-center">
                                    <input type="text" class="form-control" placeholder="이메일 *" id="name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="패스워드 *" id="email" required data-validation-required-message="Please enter your email address.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="별명 *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="성별 *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="생년월일 *" id="phone" required data-validation-required-message="Please enter your phone number.">
                                    <p class="help-block text-danger"></p>
                                </div>                        
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-md-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">등록하기</button>
                            </div>   
                        </div>
                    </form>
                </div>
                <div class="col-xs-3 text-center"></div>
                </div>
            </div>
    </section>


</html>