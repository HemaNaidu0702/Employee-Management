import './App.css'
import DepartmentComponent from './Components/DepartmentComponent'
import EmployeeComponent from './Components/EmployeeComponent'
import FooterComponent from './Components/FooterComponent'
import HeaderComponent from './Components/HeaderComponent'
import ListDepartmentComponent from './Components/ListDepartmentComponent'
import ListEmployeeComponent from './Components/ListEmployeeComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App () {
    return (
        <>
        <BrowserRouter>
        <HeaderComponent/>

        <Routes> 
            {/*  http://localhost:3000 */}
            <Route path='/' element={<ListEmployeeComponent/>}>  </Route>
            {/*  http://localhost:3000/employees */}
            <Route path='/employees' element={<ListEmployeeComponent/>}>  </Route>
             {/*  http://localhost:3000/add-employee */}
            <Route path='/add-employee' element={<EmployeeComponent/>}></Route>
              {/*  http://localhost:3000/edit-employee/2 */}
            <Route path='/edit-employee/:id' element={<EmployeeComponent/>} ></Route>

            <Route path='/departments' element={<ListDepartmentComponent/>} ></Route>

            <Route path='/add-department' element={<DepartmentComponent/>} ></Route>

            <Route path='/edit-department/:id' element={<DepartmentComponent/>} ></Route>
        </Routes>
        
        <FooterComponent />
        </BrowserRouter>

        </>
    )
}

export default App