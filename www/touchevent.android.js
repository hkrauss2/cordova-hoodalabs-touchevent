var exec = require('cordova/exec'),
    touchEvent = {};

touchEvent.fireAt = function(x, y) {
    exec(null, null, "HoodalabsTouchEvent", "fireAt", [x, y]);
};

module.exports = touchEvent;
