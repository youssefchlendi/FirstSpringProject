<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<title>Crأ©er un Produit</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container my-4">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<span class="anchor" id="formLogin"></span>
				<!-- form card login -->
				<div class="card card-outline-secondary">
					<div class="card-header">
						<h3 class="mb-0">Login</h3>
					</div>
					<div class="card-body">
						<c:if test="${not empty msg}">
							<div class="alert alert-success" role="alert">${msg}</div>
						</c:if>
						<form class="form" id="formLogin" name="formLogin" role="form"
							action="saveProduit" method="post">
							<div class="form-group">
								<label for="nomProduit">Nom</label> <input class="form-control"
									id="nomProduit" name="nomProduit" required="" type="text">
							</div>
							<div class="form-group">
								<label>Prix</label> <input class="form-control" id="prixProduit"
									name="prixProduit" required="" type="number">
							</div>
							<div class="form-group">
								<label>date création</label> <input class="form-control"
									id="date" name="date" required="" type="date">
							</div>
							<input class="btn btn-success my-3 btn-lg float-right"
								type="submit" value="ajouter">
						</form>
	<a class="btn  float-end" href="ListeProduits">Liste Produits</a>
					</div>
					<!--/card-block-->
				</div>
				<!-- /form card login -->
			</div>
		</div>
	</div>
</body>
</html>