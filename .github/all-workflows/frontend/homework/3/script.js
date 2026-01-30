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
