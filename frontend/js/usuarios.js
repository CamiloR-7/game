/* --- Config --- */
const BASE = "http://localhost:8080/api/users";
const $ = (id) => document.getElementById(id);

/* --- Elementos --- */
const form        = $("formUsuario");
const tabla       = $("tablaUsuarios");
const btnCancelar = $("btnCancelar");

/* --- Estado --- */
let editandoId = null;

/* --- Utilidades --- */
function getDatosForm() {
  return {
    nombre:        $("nombre").value.trim(),
    documento:     $("documento").value.trim(),
    correo:        $("correo").value.trim(),
    numeroTelefono:$("telefono").value.trim() || null   // opcional
  };
}

function setDatosForm(u) {
  $("nombre").value    = u.nombre;
  $("documento").value = u.documento;
  $("correo").value    = u.correo;
  $("telefono").value  = u.numeroTelefono ?? "";
}

function resetForm() {
  form.reset();
  editandoId = null;
  btnCancelar.hidden = true;
}

function crearFilaUsuario(u) {
  const tr = document.createElement("tr");
  tr.dataset.id = u.id;
  tr.innerHTML = `
    <td>${u.nombre}</td>
    <td>${u.documento}</td>
    <td>${u.correo}</td>
    <td>${u.numeroTelefono ?? "-"}</td>
    <td>
      <button class="editar">Editar</button>
      <button class="eliminar">Eliminar</button>
    </td>
  `;
  return tr;
}

/* --- CRUD --- */
async function guardarUsuario(u) {
  const url = editandoId ? `${BASE}/${editandoId}` : BASE;
  const metodo = editandoId ? "PUT" : "POST";

  const res = await fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(u)
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || "Error al guardar usuario");
  }
  return res.json();
}

async function eliminarUsuario(id) {
  const res = await fetch(`${BASE}/${id}`, { method: "DELETE" });
  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || "No se pudo eliminar");
  }
}

async function obtenerUsuarios() {
  const res = await fetch(BASE);
  if (!res.ok) throw new Error("Error al cargar usuarios");
  return res.json();
}

/* --- Manejadores de eventos --- */
form.addEventListener("submit", async (e) => {
  e.preventDefault();
  try {
    const usuario = getDatosForm();
    const data = await guardarUsuario(usuario);

    if (editandoId) {
      const fila = tabla.querySelector(`tr[data-id="${editandoId}"]`);
      fila.replaceWith(crearFilaUsuario(data));
    } else {
      tabla.appendChild(crearFilaUsuario(data));
    }
    resetForm();
  } catch (err) {
    alert(err.message);
  }
});

btnCancelar.addEventListener("click", resetForm);

tabla.addEventListener("click", async (e) => {
  const fila = e.target.closest("tr");
  if (!fila) return;
  const id = fila.dataset.id;

  if (e.target.classList.contains("editar")) {
    const c = fila.children;
    setDatosForm({
      id,
      nombre: c[0].textContent,
      documento: c[1].textContent,
      correo: c[2].textContent,
      numeroTelefono: c[3].textContent === "-" ? "" : c[3].textContent
    });
    editandoId = id;
    btnCancelar.hidden = false;
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  if (e.target.classList.contains("eliminar")) {
    if (confirm("¿Eliminar usuario?")) {
      try {
        await eliminarUsuario(id);
        fila.remove();
        if (editandoId == id) resetForm();
      } catch (err) {
        alert(err.message);
      }
    }
  }
});

/* --- Inicialización --- */
(async function cargarUsuarios() {
  try {
    const usuarios = await obtenerUsuarios();
    tabla.innerHTML = "";
    usuarios.forEach(u => tabla.appendChild(crearFilaUsuario(u)));
  } catch (err) {
    alert(err.message);
  }
})();
