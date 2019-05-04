<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <title>Transactions|PocketBank</title>

    <style type="text/css">
        <%@include file="styles.css"%>
    </style>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <span class="navbar-brand"><i class="fas fa-wallet"></i> PocketBank</span>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-money-check-alt"></i> Account</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="fas fa-credit-card"></i> Cards</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="transaction.jsp"><i class="fas fa-exchange-alt"></i> Transactions</a>
            </li>
            <li class="nav-item right">
                <span class="nav-link disabled"><i class="far fa-user-circle"></i> ${user.getFirstName()} ${user.getLastName()}</span>
            </li>
            <li class="nav-item right">
                <a class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-4"></div>
        <div class="col-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Payments and transfers</h5>
                    <form action="transaction" method="post">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Source card</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01" name="sourceCard">
                                <c:forEach var="item" items="${cardsList}">
                                    <option>
                                        <c:out value="${item.id}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Destination card</span>
                            </div>
                            <input type="number" class="form-control" aria-label="Default" name="destinationCard" aria-describedby="inputGroup-sizing-default" min="0" max="2147483647" required>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-default">Amount</span>
                            </div>
                            <input type="number" class="form-control" aria-label="Default" name="amount" aria-describedby="inputGroup-sizing-default" min="0" max="2147483647" step="0.01" required>
                        </div>
                        <button type="submit" class="btn btn-primary form-control">Confirm transfer</button>
                    </form>
                    <c:out value="${resultInfo}"/>
                </div>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
