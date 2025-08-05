import {
  getJuegos,
  crearJuego,
  actualizarJuego,
  eliminarJuego,
  getGeneros,
  getClasificaciones
} from "./api.js";

const $ = (id) => document.getElementById(id);

const form = $("formJuego");
const tabla = $("tablaJuegos");
const btnCancelar = $("btnCancelar");

let editandoId = null;

function getDatosForm() {
  return {
    titulo: $("titulo").value,
    desarrollador: $("desarrollador").value,
    calificacion: parseFloat($("calificacion").value),
    fechaSalida: $("fechaSalida").value,
    precio: parseFloat($("precio").value),
    generoId: parseInt($("generoId").value),
    clasificacionId: parseInt($("clasificacionId").value),
    stock: parseInt($("stock").value),
  };
}

function setDatosForm(game) {
  $("titulo").value = game.titulo;
  $("desarrollador").value = game.desarrollador;
  $("calificacion").value = game.calificacion;
  $("fechaSalida").value = game.fechaSalida;
  $("precio").value = game.precio;
  $("stock").value = game.stock;
  // Los select se reinician manualmente
}

function resetForm() {
  form.reset();
  editandoId = null;
  btnCancelar.hidden = true;
}

function crearFilaJuego(game) {
  const tr = document.createElement("tr");
  tr.dataset.id = game.id;
  tr.innerHTML = `
    <td>${game.titulo}</td>
    <td>${game.desarrollador}</td>
    <td>${game.calificacion}</td>
    <td>${game.fechaSalida}</td>
    <td>$${parseFloat(game.precio).toFixed(2)}</td>
    <td>${game.genero}</td>
    <td>${game.clasificacion}</td>
    <td>${game.stock}</td>
    <td>
      <button class="editar">Editar</button>
      <button class="eliminar">Eliminar</button>
    </td>
  `;
  return tr;
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  try {
    const juego = getDatosForm();
    const data = editandoId
      ? await actualizarJuego(editandoId, juego)
      : await crearJuego(juego);

    if (editandoId) {
      const fila = tabla.querySelector(`tr[data-id="${editandoId}"]`);
      fila.replaceWith(crearFilaJuego(data));
    } else {
      tabla.appendChild(crearFilaJuego(data));
    }

    resetForm();
  } catch (err) {
    alert(err.message);
  }
});

btnCancelar.addEventListener("click", resetForm);

tabla.addEventListener("click", async (e) => {
  const fila = e.target.closest("tr");
  const id = fila.dataset.id;

  if (e.target.classList.contains("editar")) {
    const celdas = fila.children;
    setDatosForm({
      id,
      titulo: celdas[0].textContent,
      desarrollador: celdas[1].textContent,
      calificacion: celdas[2].textContent,
      fechaSalida: celdas[3].textContent,
      precio: parseFloat(celdas[4].textContent.replace("$", "")),
      genero: celdas[5].textContent,
      clasificacion: celdas[6].textContent,
      stock: celdas[7].textContent,
    });
    editandoId = id;
    btnCancelar.hidden = false;
    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  if (e.target.classList.contains("eliminar")) {
    if (confirm("¿Eliminar juego?")) {
    try {
    await eliminarJuego(id);
    fila.remove();
    } catch (err) {
    alert("No se pudo eliminar: " + err.message);
    }
      if (editandoId == id) resetForm();
    }
  }
});

async function cargarJuegos() {
  try {
    const juegos = await getJuegos();
    tabla.innerHTML = "";
    juegos.forEach((juego) => tabla.appendChild(crearFilaJuego(juego)));
  } catch (err) {
    alert("Error al cargar juegos: " + err.message);
  }
}

async function cargarSelects() {
  try {
    const [generos, clasificaciones] = await Promise.all([
      getGeneros(),
      getClasificaciones()
    ]);

    const generoSelect = $("generoId");
    const clasifSelect = $("clasificacionId");

    generos.forEach(g => {
      const option = document.createElement("option");
      option.value = g.id;
      option.textContent = g.genero;
      generoSelect.appendChild(option);
    });

    clasificaciones.forEach(c => {
      const option = document.createElement("option");
      option.value = c.id;
      option.textContent = c.clasificacion;
      clasifSelect.appendChild(option);
    });
  } catch (err) {
    alert("Error al cargar selects: " + err.message);
  }
}


cargarJuegos();
cargarSelects();
