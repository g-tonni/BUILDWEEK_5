import { BrowserRouter, Routes, Route } from "react-router-dom"
import "./App.css"
import MyForm from "./assets/components/MyForm"
import MyFormR from "./assets/components/MyFormR"
import "bootstrap/dist/css/bootstrap.min.css"
import MyNav from "./assets/components/MyNav"
import Home from "./assets/components/Home"
import MyFatture from "./assets/components/MyFatture"

function App() {
  return (
    <>
      <BrowserRouter>
        <MyNav />
        <Routes>
          <Route path="/login" element={<MyForm />} />
          <Route path="/signUp" element={<MyFormR />} />
          <Route path="/home" element={<Home />} />
          <Route path="/fatture" element={<MyFatture />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
