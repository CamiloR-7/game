const API = "http://localhost:8080/api";
const LOANS = `${API}/loans`;

const $ = (id) => document.getElementById(id);

const form   = $("formPrestamo");
const tabla  = $("tablaPrestamos");
const btnCan = $("btnCancelar");

let editandoId = null; // por si luego deseas editar

/* ---------- Helpers ---------- */
function crearFilaPrestamo(p) {
  const tr = document.createElement("tr");
  tr.dataset.id = p.id;
  tr.innerHTML = `
    <td>${p.cliente}</td>
    <td>${p.juego}</td>
    <td>${p.fechaPrestamo}</td>
    <td>${p.fechaLimite}</td>
    <td>$${parseFloat(p.total).toFixed(2)}</td>
    <td>
      <button class="devolver">Devolver</button>
    </td>
  `;
  return tr;
}

/* ---------- Cargar selects dinámicos ---------- */
async function cargarSelects() {
  try {
    // Usuarios
    const resU = await fetch(`${API}/users`);
    const usuarios = await resU.json();
    const selU = $("usuarioId");
    usuarios.forEach(u => {
      const op = document.createElement("option");
      op.value = u.id;
      op.textContent = u.nombre;
      selU.appendChild(op);
    });

    // Juegos
    const resG = await fetch(`${API}/games`);
    const juegos = await resG.json();
    const selG = $("juegoId");
    juegos.forEach(g => {
      const op = document.createElement("option");
      op.value = g.id;
      op.textContent = g.titulo;
      selG.appendChild(op);
    });
  } catch (err) {
    alert("Error al cargar usuarios o juegos");
  }
}

/* ---------- CRUD de préstamos ---------- */
async function obtenerPrestamos() {
  const res = await fetch(LOANS);
  if (!res.ok) throw new Error("Error al cargar préstamos");
  return res.json();
}

async function crearPrestamo(data) {
  const res = await fetch(LOANS, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error("Error al crear préstamo");
  return res.json();
}

async function devolverPrestamo(id) {
  const res = await fetch(`${LOANS}/${id}/devolver`, { method: "PUT" });
  if (!res.ok) throw new Error("No se pudo devolver");
  return res.json();
}

/* ---------- Eventos ---------- */
form.addEventListener("submit", async (e) => {
  e.preventDefault();
  try {
    const payload = {
      userId:  parseInt($("usuarioId").value),
      gameId:  parseInt($("juegoId").value),
      fechaLimite: $("fechaLimite").value
    };
    const nuevo = await crearPrestamo(payload);
    tabla.appendChild(crearFilaPrestamo(nuevo));
    form.reset();
  } catch (err) {
    alert(err.message);
  }
});

tabla.addEventListener("click", async (e) => {
  const fila = e.target.closest("tr");
  if (!fila) return;
  const id = fila.dataset.id;

  if (e.target.classList.contains("devolver")) {
    if (confirm("Marcar como devuelto?")) {
      try {
        await devolverPrestamo(id);
        fila.children[5].textContent = "Devuelto";
        e.target.remove(); // quita el botón
      } catch (err) {
        alert(err.message);
      }
    }
  }
});

/* ---------- Inicialización ---------- */
(async function init() {
  await cargarSelects();
  try {
    const prestamos = await obtenerPrestamos();
    prestamos.forEach(p => tabla.appendChild(crearFilaPrestamo(p)));
  } catch (err) {
    alert(err.message);
  }
})();
