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