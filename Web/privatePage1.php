<?php
session_start();
include "top.php";
?>
<body>
	<h1>Private Page 1</h1>
<?php
echo "Active User " . $_SESSION["activeUser"]
?>
<?php if ($_SESSION["login"] !=  1){
	print ("Must Login First2");
	include ("login.php");
}?>


</body>
</html>