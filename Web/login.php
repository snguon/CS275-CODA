<?php
include "top.php";
?>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>Login</title>
<div class="col-md-6 col-xl-5 mb-4">
  <form id = "Login" method= "post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
    <!--Form-->
    <div class="card wow fadeInRight" data-wow-delay="0.3s">
      <div class="card-body">
        <!--Header-->

        <div class="text-center">
          <h3 class="white-text">
            <i class="fa fa-user white-text"></i> Login:</h3>
            <hr class="hr-light">
          </div>
          <!--Body-->
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
            <button class="btn btn-indigo" name = "buttonLogin" id="buttonLogin">Login</button>
            <hr class="hr-light mb-3 mt-4">
            <div class="inline-ul text-center d-flex justify-content-center">
              <?php if (isset($_POST['buttonLogin'])){?>
                <?php $whereCount = 1; ?>
                <?php $conditionscount=0; ?>
                <?php $quotescount=0; ?>
                <?php $symbolcount=0;?>
                <?php $whereClause = "";?>
                <?php $query = 'SELECT fldEmail, fldPassword FROM tblUsers WHERE fldEmail = ?'?>
                <?php $enteredUserdata = array()?>
                <?php $emailValue = htmlspecialchars($_POST['emailField'])?>
                <?php $passValueNEW = htmlspecialchars($_POST['passwordField'])?>

                <?php $newpassHashed = password_hash($passValueNEW,PASSWORD_DEFAULT) ?>
                <?php $verifyTest =  password_verify ($passValue, $passHashed)?>
                <?php $enteredUserdata[] = $emailValue ?>
                <?php if ($emailValue != "" AND $emailValue != ""){$records = $thisDatabaseReader->select($query, $enteredUserdata, $whereCount, $conditionscount, $quotescount, $symbolcount, false, false);?>
                  <?php foreach ($records as $record) {$dbHash = ((string)$record['fldPassword']);}?>

                  
                  <?php if (password_verify($passValueNEW,$dbHash)) {
                    ?>
                    <h1> Password is valid! Logging in... you will be directed to the main page. Thank you!</h1>
                  <?php }else {
                    echo 'Invalid password' ."<br>";}?>
                  <?php }?>
                <?php }?>
              </a>
            </div>
          </div>
        </div>

      </div>
    </form>

    <!--/.Form-->
