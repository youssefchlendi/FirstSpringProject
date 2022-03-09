<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="windows-1256">
<title>Modifier un Produit</title>
</head>
<body>
		<div class="container my-4">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<span class="anchor" id="formLogin"></span>
				<!-- form card login -->
				<div class="card card-outline-secondary">
					<div class="card-header">
						<h3 class="mb-0">Produit</h3>
					</div>
					<div class="card-body">
						<form class="form" id="formLogin" name="formLogin" role="form"
							action="updateProduit" method="post">
							<div class="form-group">
								<label for="nomProduit">Nom</label> <input class="form-control"
									id="nomProduit" name="nomProduit" value="${produit.nomProduit}" required="" type="text">
							</div>
							<div class="form-group">
								<label>Prix</label> <input class="form-control" id="prixProduit"
									name="prixProduit" required="" value="${produit.prixProduit}" type="number">
							</div>
							<div class="form-group">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${produit.dateCreation}"
				var="formatDate" />
							
								<label>date création</label> <input class="form-control"
									id="date" name="date" required="" value="${formatDate}" type="date">
							</div>
							<input class="btn btn-success my-3 btn-lg float-right"
								type="submit" value="Modifier">
								<input type="hidden" name="idProduit" value="${produit.idProduit}">
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