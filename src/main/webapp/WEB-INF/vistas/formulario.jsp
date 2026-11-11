<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de paciente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<main class="container py-4">
    <div class="card shadow-sm mx-auto" style="max-width: 760px;">
        <div class="card-header bg-primary text-white"><h1 class="h4 mb-0">${paciente == null ? 'Nuevo paciente' : 'Editar paciente'}</h1></div>
        <div class="card-body">
            <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
            <form method="post" action="${pageContext.request.contextPath}/pacientes">
                <input type="hidden" name="id" value="${paciente.id}">
                <div class="row g-3">
                    <div class="col-md-6"><label class="form-label">Nombres *</label><input class="form-control" name="nombres" required maxlength="100" value="<c:out value='${paciente.nombres}'/>"></div>
                    <div class="col-md-6"><label class="form-label">Apellidos *</label><input class="form-control" name="apellidos" required maxlength="100" value="<c:out value='${paciente.apellidos}'/>"></div>
                    <div class="col-md-6"><label class="form-label">DNI *</label><input class="form-control" name="dni" required pattern="[0-9]{8}" maxlength="8" value="<c:out value='${paciente.dni}'/>"></div>
                    <div class="col-md-6"><label class="form-label">Fecha de nacimiento *</label><input class="form-control" type="date" name="fechaNacimiento" required value="${paciente.fechaNacimiento}"></div>
                    <div class="col-md-6"><label class="form-label">Teléfono</label><input class="form-control" name="telefono" maxlength="15" value="<c:out value='${paciente.telefono}'/>"></div>
                    <div class="col-md-6"><label class="form-label">Correo</label><input class="form-control" type="email" name="correo" maxlength="120" value="<c:out value='${paciente.correo}'/>"></div>
                    <div class="col-12"><label class="form-label">Dirección</label><input class="form-control" name="direccion" maxlength="180" value="<c:out value='${paciente.direccion}'/>"></div>
                </div>
                <div class="mt-4 d-flex gap-2">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                    <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/pacientes">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
</html>
