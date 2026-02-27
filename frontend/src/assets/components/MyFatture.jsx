import { Container, Row, Col, Card } from "react-bootstrap"
import { useEffect, useState } from "react"

function MyFatture() {
  const port = import.meta.env.VITE_PORT
  const url = `http://localhost:${port}/fatture/search`

  const [data, setData] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch(url, {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Errore nella response")
        }
        return res.json()
      })
      .then((json) => {
        setData(json.content)
      })
      .catch((err) => {
        console.error(err)
        setError(err.message)
      })
      .finally(() => {
        setLoading(false)
      })
  }, [url])

  if (loading) return <p>Caricamento...</p>
  if (error) return <p>Errore: {error}</p>

  return (
    <>
      <Container>
        <h1 className="text-center">Tutte le fatture</h1>
        <Row className="mt-5">
          {data.map((fattura) => (
            <Col key={fattura.idFattura} xs={12} sm={6} md={4} lg={3}>
              <Card
                key={fattura.idFattura}
                style={{ width: "18rem", margin: "10px" }}
              >
                <Card.Body>
                  <Card.Title>Fattura #{fattura.idFattura}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">
                    Cliente: {fattura.cliente.nomeCliente}
                  </Card.Subtitle>
                  <Card.Text>Importo: €{fattura.importoFattura}</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
    </>
  )
}

export default MyFatture
