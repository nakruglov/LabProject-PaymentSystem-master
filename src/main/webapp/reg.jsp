<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <title>Registration|PocketBank</title>

    <style type="text/css">
        <%@include file="styles.css"%>
    </style>

</head>
<body class="bg-dark">
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-md"></div>
        <div class="col-md">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Create an account</h5>
                    <h6 class="card-subtitle mb-2 text-muted"><i class="fas fa-wallet"></i> PocketBank</h6>
                    <form method="post" action="reg">
                        <div class="form-group">
                            <input type="firstname" class="form-control" name="firstname" id="firstname" placeholder="First name, only latin letters"
                                   pattern="^[a-zA-Z]+$" required>
                        </div>
                        <div class="form-group">
                            <input type="lastname" class="form-control" name="lastname" id="lastname" placeholder="Last name, only latin letters"
                                   pattern="^[a-zA-Z]+$" required>
                        </div>
                        <div class="form-group">
                            <input type="login" class="form-control" name="login" id="login" placeholder="Enter login" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="repeatpassword" id="repeatpassword" placeholder="Repeat password" required>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary form-control" value="Sign Up">
                        </div>
                    </form>
                    <a href="/login" class="link">Already registered?</a>
                </div>
            </div>
        </div>
        <div class="col-md"></div>
    </div>
</div>
<script>
    <%@include file="validPass.js"%>
</script>
</body>
</html>
