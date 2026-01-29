const employee1 = {
id: 1,
name: "lion",
age: 30,
salary: 85000,
department: "Engineering",
skills: ["JavaScript", "React", "Node.js"],
experience: 5,
getFullInfo: function() {
    return `Name: ${this.name} , age: ${this.ag}, salary: ${this.salary}, department: ${this.department}, skills: ${this.skills}, experience: ${this.experience}`
},
compareEmployees: function(emp2) {
    if(this.skills.length > emp2.skills.length) {
        return `hasMoreSkills(${this.name})`
    }
    return `hasMoreSkills(${emp2.name})`
}
}

function createEmployeeobject(id,name,age,salary,department,experience,skills) {
    const employee = Object.create(employee1);
    employee.id = id;
    employee.name = name;
    employee.age = age;
    employee.salary = salary;
    employee.department = department;
    employee.experience = experience;
    employee.skills = skills;
    return employee;
}

const employee2 = createEmployeeobject(2,"bear",25,90000,"Testing",5,["JavaScript", "React"]);
const employee3 = createEmployeeobject(3,"simha",24,91000,"Develpoer",4,["JavaScript"]);
const employee4 = createEmployeeobject(4,"ling",23,92000,"Develpoer",4,["JavaScript","React"]);
const employee5 = createEmployeeobject(5,"ming",22,93000,"Engineering",3,["JavaScript","React"]);


// modifying object properties

function getEmployeeInfo(employee) {
    return `${employee.name} works in ${employee.department} department and earns ${employee.salary}`
}

function addSkill(employee, skill) {
    employee.skills.push(skill)
}


const employees = [employee1,employee2,employee3,employee4,employee5]
console.log(employees)


function filterByExperience(employees, minExperience) {
    return employees.filter(employee => employee.experience >= minExperience)
}


function summarizeEmployeeDetails(employees) {
    return employees.map(employee => `${employee.name}(${employee.department}) - $${employee.salary}`)
}

function calculateAvgSalary(employees) {
    let sum = employees.reduce((accumulator,employee) => accumulator+employee.salary,0)

    return sum / employees.length;
}

function countEmployeesWithRespectToDepartments(employees) {
    const map = new Map();

    for(const employee of employees) map.set(employee.department,(map.get(employee.department) ?? 0) + 1)

    return map;
}


function gethighestPaidEmployee(employees) {
    let highPaidEmployee = null;

    for(const employee of employees) {
        if(highPaidEmployee === null || employee.salary > highPaidEmployee.salary) {
            highPaidEmployee = employee
        }
    }

    return `Highest paid employee: Name: ${highPaidEmployee.name}, Salary:${highPaidEmployee.salary}`;
} 


function sortEmployeesBYExperience(employees) {
    return employees.sort((emp1,emp2) => emp2.experience - emp1.experience)
} 


function extractNameDepartmentSalary(employee) {
    const {name,department,salary} = employee;
    return {name,department,salary};
}

function destructureTopPaidEmployees(employees,minSalary) {
    const [...a] = employees.filter(employee => employee.salary >= minSalary);
    return a
}

function destructureTopPaidEmployees(employees,maxSalary) {
    const [...a] = employees.filter(employee => employee.salary <= minSalary);
    return a
}

function mergeEmployeeSkills(emp1,emp2) {
    const skills = [...emp1.skills,...emp2.skills]
    return new Set(skills); 
}

function countEmployees(...employees) {
    return employees.length
}

function findAverageAge(...employees) {
    let age = employees.reduce((accumulator,employee) => accumulator+employee.age,0)
    return age / employees.length
}


function getAnalytics(employees) {
    const map = new Map();

    for(const employee of employees) {
        for(const skill of employee.skills) {
            map.set(skill,(map.get(skill) ?? 0) + 1)
        }
    }

    return map;
}


