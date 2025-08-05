const BASE = "http://localhost:8080/api";

export async function getJuegos() {
  const res = await fetch(`${BASE}/games`);
  if (!res.ok) throw new Error("No se pudieron obtener los juegos");
  return res.json();
}

export async function crearJuego(juego) {
  const res = await fetch(`${BASE}/games`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(juego),
  });
  if (!res.ok) throw new Error("Error al crear juego");
  return res.json();
}

export async function actualizarJuego(id, juego) {
  const res = await fetch(`${BASE}/games/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(juego),
  });
  if (!res.ok) throw new Error("Error al actualizar juego");
  return res.json();
}

export async function eliminarJuego(id) {
  const res = await fetch(`http://localhost:8080/api/games/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg || "Error al eliminar juego");
  }
}

export async function getGeneros() {
  const res = await fetch(`${BASE}/genres`);
  if (!res.ok) throw new Error("No se pudieron cargar los géneros");
  return res.json();
}

export async function getClasificaciones() {
  const res = await fetch(`${BASE}/classifications`);
  if (!res.ok) throw new Error("No se pudieron cargar las clasificaciones");
  return res.json();
}
