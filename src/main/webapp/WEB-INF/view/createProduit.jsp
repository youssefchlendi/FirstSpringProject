<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Créer un Produit</title>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript"
	src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<meta charset="UTF-8">

</head>
<body>
	<div class="container my-4">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<span class="anchor" id="formLogin"></span>
				<!-- form card login -->
				<div class="card card-outline-secondary">
					<div class="card-header">
						<h3 class="mb-0">Créer produit</h3>
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