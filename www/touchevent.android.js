var exec = require('cordova/exec'),
    touchEvent = {};

touchEvent.fireAt = function(x, y) {
    exec(null, null, "HoodalabsTouchEvent", "touchEvent", ['fireat', x, y]);
};

module.exports = touchEvent;
