import { Container, Row, Col, Card, Button, Form } from "react-bootstrap"
import { useEffect, useState } from "react"

function MyFatture() {
  const port = import.meta.env.VITE_PORT
  const [param, setParam] = useState("")
  const url = `http://localhost:${port}/fatture/search${param}`
  const [data, setData] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const fetchFatture = () => {
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
  }

  useEffect(() => {
    fetchFatture()
  }, [])

  if (loading) return <p>Caricamento...</p>
  if (error) return <p>Errore: {error}</p>

  return (
    <>
      <Container>
        <h1 className="text-center">Tutte le fatture</h1>
        <Row>
          <Col>
            <Form
              className="mb-3"
              onSubmit={(e) => {
                e.preventDefault()
                fetchFatture()
              }}
            >
              <Form.Group className="mb-3">
                <Form.Label>Id cliente</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter id cliente"
                  onChange={(e) => {
                    setParam(`?idCliente=${e.target.value}`)
                  }}
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Filtra
              </Button>
            </Form>
            <Form
              className="mb-3"
              onSubmit={(e) => {
                e.preventDefault()
                fetchFatture()
              }}
            >
              <Form.Group className="mb-3">
                <Form.Label>Stato fattura</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter stato fattura"
                  onChange={(e) => {
                    setParam(`?statoFattura=${e.target.value}`)
                  }}
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Filtra
              </Button>
            </Form>
            <Form
              className="mb-3"
              onSubmit={(e) => {
                e.preventDefault()
                fetchFatture()
              }}
            >
              <Form.Group className="mb-3">
                <Form.Label>Data emissione</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter data emissione"
                  onChange={(e) => {
                    setParam(`?dataEmissione=${e.target.value}`)
                  }}
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Filtra
              </Button>
            </Form>
            <Form
              className="mb-3"
              onSubmit={(e) => {
                e.preventDefault()
                fetchFatture()
              }}
            >
              <Form.Group className="mb-3">
                <Form.Label>Minimo importo</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Enter minimo importo"
                  onChange={(e) => {
                    setParam(`?minImporto=${e.target.value}`)
                  }}
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Massimo importo</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Enter massimo importo"
                  onChange={(e) => {
                    setParam(`?maxImporto=${e.target.value}`)
                  }}
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Filtra
              </Button>
            </Form>
          </Col>
        </Row>
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
