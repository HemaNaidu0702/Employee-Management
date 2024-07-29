import axios from "axios";


// This service class is used to make rest api calls

const REST_API_BASE_URL = "http://localhost:8080/api/employees"

export const listEmployees = () => axios.get(REST_API_BASE_URL);

export const createEmployee = (employee) => axios.post(REST_API_BASE_URL, employee);

export const getemployee = (employeeId) => axios.get(REST_API_BASE_URL+ '/' + employeeId);

export const updateemployee = (employeeId, employee) => axios.put(REST_API_BASE_URL+ '/' + employeeId, employee);

export const deleteemployee = (employeeId) => axios.delete(REST_API_BASE_URL + '/' + employeeId);