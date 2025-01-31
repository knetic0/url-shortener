import { BrowserRouter, Route, Routes } from "react-router";
import AuthLayout from "../layouts/auth-layout";
import Login from "../views/auth/login";
import Register from "../views/auth/register";
import MainLayout from "../layouts/main-layout";
import Home from "../views/home";

const Router = () => {
    return (
        <BrowserRouter basename={process.env.PUBLIC_URL}>
            <Routes>
                <Route element={<AuthLayout />}>
                    <Route path="/" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                </Route>
                <Route element={<MainLayout />}>
                    <Route path="/home" element={<Home />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default Router;