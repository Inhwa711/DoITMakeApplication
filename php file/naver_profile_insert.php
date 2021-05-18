<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('navercon.php');

    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

    if((($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {

        $id=$_POST["id"];
        $nickname=$_POST["nickname"];
        $gender=$_POST["gender"];


        if(empty($id)){
            $errMSG = "id를 입력하세요.";
        }
        else if(empty($nickname)){
            $errMSG = "nickname을 입력하세요.";
        }
        else if(empty($gender)){
            $errMSG = "gender를 입력하세요.";
        }

        if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO naver_profile (id, nickname, gender) VALUES(:id, :nickname, :gender)');
                $stmt->bindParam(':id', $id);
                $stmt->bindParam(':nickname', $nickname);
	  $stmt->bindParam(':gender', $gender);

                if($stmt->execute())
                {
                    $successMSG = "새로운 사용자를 추가했습니다.";
                }
                else
                {
                    $errMSG = "사용자 추가 에러";
                }

            } catch(PDOException $e) {
                die("Database error: " . $e->getMessage()); 
            }
        }
    }
?>
<?php 
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
   
    if( !$android )
    {
?>
<html>
   <body>
        
        <form action="<?php $_PHP_SELF ?>" method="POST">
            id: <input type = "text" name = "id" />
            nickname: <input type = "text" name = "nickname" />
            gender: <input type = "text" name = "gender" />
            <input type = "submit" name = "submit" />
        </form>
   
   </body>
</html>
<?php 
    }
?>
