import React, { useEffect, useState } from 'react'
import { createEmployee, getemployee, updateemployee } from '../services/EmployeeService'
import {useNavigate, useParams} from 'react-router-dom'
import { getAllDepartments } from '../services/DepartmentService'

const EmployeeComponent = () => {

  
   const[firstname, setfirstname] = useState('')
   const[lastname, setlastname] = useState('')
   const[email,setemail] = useState('')
   const[departmentId,setDepartmentId] = useState('')
   const[departments,setdepartments] = useState([])

   useEffect(()=> {
    
       getAllDepartments().then((response) => {
           setdepartments(response.data)
       }).catch(error => {
           console.error(error)
       })
    
  }, [])

   const[errors, setErrors] = useState({
    firstname:'',
    lastname:'',
    email:'',
    department: ''
   })

   const navigator = useNavigate();
   const {id}= useParams();


   useEffect(()=> {
     if(id) {
        getemployee(id).then((response) => {
            setfirstname(response.data.firstname);
            setlastname(response.data.lastname);
            setemail(response.data.email);
        }).catch(error => {
            console.error(error)
        })
     }
   }, [id])

   function saveAndUpdateEmployee(event) {
    event.preventDefault();

    if(validateForm()) {
        const employee= {firstname, lastname, email, departmentId}
        console.log(employee)

        if(id) {
            updateemployee(id, employee).then((response) => {
                navigator('/employees')
            }).catch(error => {
                console.error(error);
            })
        } else {
            createEmployee(employee).then((response) =>{
                console.log(response.data)
                navigator('/employees')
            }).catch(error => {
                console.error(error);
            })
        }
    }

   }


   function validateForm() {
      let valid = true

      const errorscopy = {...errors}

      if(firstname.trim()) {
        errorscopy.firstname = '';
      } else {
        errorscopy.firstname = 'First name is required';
        valid = false
      }

      if(lastname.trim()) {
        errorscopy.lastname = '';
      } else {
        errorscopy.lastname = 'Last name is required';
        valid = false
      }

      if(email.trim()) {
        errorscopy.email = '';
      } else {
        errorscopy.email = 'Email is required';
        valid = false
      }

      if(departmentId){
        errorscopy.department = ''
    }else {
        errorscopy.department = 'Select Department'
        valid = false
    }

      setErrors(errorscopy);
      return valid;
   }


  function pageTitle() {
    if(id) {
        return <h2 className="text-center">Update Employee</h2>
    }else {
       return <h2 className="text-center">Add Employee</h2>
    }
  }

  return (
    <div className="container-md">
        <br /> <br/>
        <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
                {
                      pageTitle()
                }
                <div className="card-body">
                    <form>
                        <div className="form-group mb-2">
                            <label className="form-label">First Name</label>
                            <input
                             type='text'
                             placeholder='Enter First Name'
                             name='firstname'
                             value={firstname}
                             className={`form-control ${ errors.firstname ? 'is-invalid' : ''}`}
                             onChange={(event) => setfirstname(event.target.value)}
                            >
                            </input>
                            {errors.firstname && <div className='invalid-feedback'>{errors.firstname}</div>}
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label">Last Name</label>
                            <input
                             type='text'
                             placeholder='Enter Last Name'
                             name='lastname'
                             value={lastname}
                             className={`form-control ${ errors.lastname ? 'is-invalid' : ''}`}
                             onChange={(event) => setlastname(event.target.value)}
                            >
                            </input>
                            {errors.lastname && <div className='invalid-feedback'>{errors.lastname}</div>}
                        </div>

                        <div className="form-group mb-2">
                            <label className="form-label">Email</label>
                            <input
                             type='text'
                             placeholder='Enter Email'
                             name='email'
                             value={email}
                             className={`form-control ${ errors.email ? 'is-invalid' : ''}`}
                             onChange={(event) => setemail(event.target.value)}
                            >
                            </input>
                            {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Select Department:</label>
                            <select
                               className={`form-control ${ errors.department ? 'is-invalid': '' }`}
                               value={departmentId}
                                onChange={(e) => setDepartmentId(e.target.value)}
                            >
                               <option value="Select Department">Select Department</option>
                                {
                                    departments.map( department => 
                                        <option key={department.id} value={department.id} > {department.departmentName}</option>
                                        )
                                }
                            </select>
                            { errors.department && <div className='invalid-feedback'> { errors.department} </div> }
                        </div>


                        <button className='btn btn-success' onClick={saveAndUpdateEmployee}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent