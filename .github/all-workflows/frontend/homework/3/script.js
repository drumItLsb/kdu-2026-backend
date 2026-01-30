function Task(title, priority) {
    this.id = Date.now();
    this.title = title;
    this.priority = priority;
    this.completed = false;
}

Task.prototype.markComplete = function() {
    this.completed = true;
    return this;
}

Task.prototype.updatePriority = function(newPriority) {
    if(newPriority === "low" ||  newPriority === "medium" || newPriority === "high") {
        this.priority = newPriority;
    } else throw new Error("priority should be one of these low, medium, or high");
    
    return this;
}

Task.prototype.getInfo = function() {
    return `id: ${this.id}, title: ${this.title}, priority: ${this.priority}, completed: ${this.completed}`
}

function PriorityTask(title, priority,dueDate = null) {
    Task.call(title,priority);
    this.dueDate = dueDate;
}

// extending Task prototype
PriorityTask.prototype = Object.create(Task.prototype)

// specifying to call the PriorityTask constructor
PriorityTask.prototype.constructor = PriorityTask;

PriorityTask.prototype.getInfo = function() {
    if(this.dueDate !== null) {
        return `id: ${this.id}, title: ${this.title}, priority: ${this.priority}, completed: ${this.completed}, dueDate: ${this.dueDate}`
    }

    return Task.prototype.getInfo(this);
}

Task.prototype.getAllTasksInfo = function(tasks) {
    const infoStringArray = [];

    for(const task of tasks) {
        infoStringArray.push(task.getInfo())
    }

    return infoStringArray;
}


function createTaskAsync(title, priority) {
    console.log("Creating tasks…")

    return new Promise((resolve,reject) => {
        setTimeout(() => {
            console.log("Task created!")
            resolve(new Task(title,priority));
        },1000)
    })
}

function demonstrateEventLoop() {
    let delay = 2000;
    setTimeout(() => {
            console.log(1)
    },delay);

    delay += 2;

    for(let i=4;i>1;i--) {
        setTimeout(() => {
            console.log(i)
        },delay);
        delay += 2;
    }
}

async function createAndSaveTask(title, priority) {
 const task1 = await createTaskAsync(title,priority);
 const task2 = await createTaskAsync(title+"2",priority);

 console.log("Task created and saved successfully!")

 return [task1,task2]
}

async function createMultipleTasksAsync(taskDataArray) {
    const pendingTasks = taskDataArray.map(task => createTaskAsync(task.title,task.priority));
    const createdTasks = await Promise.all(pendingTasks);
    console.log("Creating X tasks…");
    console.log("All tasks created!");
    return createdTasks
}