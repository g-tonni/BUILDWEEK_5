import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from "react-router-dom";
function MyNav() {
   return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand as={Link} to="/home">Epic Energy</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/logIn">LogIn</Nav.Link>
            <Nav.Link as={Link} to="/signUp">SignUp</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <br />
    </>
  );
}
export default MyNav;