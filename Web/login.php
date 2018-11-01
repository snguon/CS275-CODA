<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Login</title>
<div class="col-md-6 col-xl-5 mb-4">
  <form id = "Register" method= "post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
                    <!--Form-->
                    <div class="card wow fadeInRight" data-wow-delay="0.3s">
                      <div class="card-body">
                        <!--Header-->
                        
                        <div class="text-center">
                          <h3 class="white-text">
                            <i class="fa fa-user white-text"></i> Register:</h3>
                          <hr class="hr-light">
                        </div>
                        <!--Body-->
                        <div class="md-form">
                          <i class="fa fa-user prefix white-text active"></i>
                          <input type="text" id="nameField" class="white-text form-control">
                          <label for="nameField" name = "nameField" class="active">Your name</label>
                        </div>
                        <div class="md-form">
                          <i class="fa fa-envelope prefix white-text active"></i>
                          <input type="email" id="emailField" name = "emailField" class="white-text form-control">
                          <label for="emailField" class="active">Your email</label>
                        </div>
                        <div class="md-form">
                          <i class="fa fa-lock prefix white-text active"></i>
                          <input type="password" name = "passwordField" id="passwordField" class="white-text form-control">
                          <label for="passwordField">Your password</label>
                        </div>
                        <div class="text-center mt-4">
                          <button class="btn btn-indigo">Sign up</button>
                          <hr class="hr-light mb-3 mt-4">
                          <div class="inline-ul text-center d-flex justify-content-center">
                            <?php $nameValue = htmlspecialchars($_POST['nameField'])?>
                            <?php $emailValue = htmlspecialchars($_POST['emailField'])?>
                            <?php $passValue = htmlspecialchars($_POST['passwordField'])?>
                            <?php $passHashed = password_hash($passValue,PASSWORD_DEFAULT) ?>
                            <?php $verifyTest =  password_verify ($passValue, $passHashed)?>
                            <?php if ($verifyTest == 1)
                              echo ("Success")
                            ?>
                            </a>
                          </div>
                        </div>
                      </div>
                      
                    </div>
                  </form>
                    
                    <!--/.Form-->
