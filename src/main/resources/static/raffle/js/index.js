console.log("index.js");
function ScratchCard(config) {
    this.config = {
        canvas: null,
        showAllPercent: 55,
        coverColor: null,
        doneCallback: ()=>console.log("done"),
        radius: 20,
        fadeOut: 2000,
        offsetX: null,
        offsetY:null
    }
    Object.assign(this.config, config);
    this.canvas = this.config.canvas;
    this.ctx = null;
    this.offsetX = 200;
    this.offsetY = 200;
    this.isDown = false;
    this.done = false;
    this._init();
}
ScratchCard.prototype = {
    constructor: ScratchCard,
    _init: function() {
        this.ctx = this.canvas.getContext('2d');
        this.offsetX = this.canvas.offsetLeft;
        this.offsetY = this.canvas.offsetTop;
        this._addEvent();
        this.ctx.globalCompositeOperation = 'source-over';
        this.ctx.fillStyle = this.config.coverColor;
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
        this.ctx.globalCompositeOperation = 'destination-out';
    },
    _addEvent: function() {
        this.canvas.addEventListener('touchstart', this._eventDown.bind(this), { passive: false });
        this.canvas.addEventListener('touchend', this._eventUp.bind(this), { passive: false });
        this.canvas.addEventListener('touchmove', this._scratch.bind(this), { passive: false });
        this.canvas.addEventListener('mousedown', this._eventDown.bind(this), { passive: false });
        this.canvas.addEventListener('mouseup', this._eventUp.bind(this), { passive: false });
        this.canvas.addEventListener('mousemove', this._scratch.bind(this), { passive: false });
    },
    _eventDown: function (e) { 
        e.preventDefault();
        this.isDown = true;
    },
    _eventUp: function (e) {
        e.preventDefault();
        this.isDown = false;
    },
    _scratch: function (e) {
        let that = this;
        e.preventDefault();
        if (!this.done && this.isDown) {
            if (e.changedTouches) {
                e = e.changedTouches[e.changedTouches.length - 1];
            }
            let canvas = this.config.canvas;
            let rect = canvas.getBoundingClientRect();
            let x = e.offsetX * (canvas.width / rect.width);
            let y = e.offsetY * (canvas.width / rect.width);
            this.ctx.beginPath();
            this.ctx.arc(x * (canvas.width / rect.width), y * (canvas.width / rect.width), that.config.radius* (canvas.width / rect.width), 0, Math.PI * 2);
            this.ctx.fill();
            if (this._getFilledPercentage() > this.config.showAllPercent) {
                this._scratchAll()
            }
        }
    },
    _scratchAll: function () {
        var that = this;
        this.done = true;
        this.canvas.style.transition = 'all ' + this.config.fadeOut / 1000 + 's linear';
        this.canvas.style.opacity = '0';
        setTimeout(function () {
            that._clear();
        }, this.config.fadeOut)
        this.config.doneCallback && this.config.doneCallback();
    },
    _clear: function() {
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
    },
    _getFilledPercentage: function() {
        var imgData = this.ctx.getImageData(0, 0, this.canvas.width, this.canvas.height);
        var pixels = imgData.data;
        var transPixels = [];
        for (var i = 0; i < pixels.length; i += 4) {
            if (pixels[i + 3] < 128) {
                transPixels.push(pixels[i + 3]);
            }
        }
        return (transPixels.length / (pixels.length / 4) * 100).toFixed(2)
    }

}
window.addEventListener('touchmove', function(e) {
    e.preventDefault();
}, {passive: false});
const card0 = new ScratchCard({
    canvas: document.querySelector('#canvas0'),
    coverColor: "#7F7F7F",
});
const card1 = new ScratchCard({
    canvas: document.querySelector('#canvas1'),
    coverColor: "#7F7F7F",
});
const card2 = new ScratchCard({
    canvas: document.querySelector('#canvas2'),
    coverColor: "#7F7F7F",
});
const card3 = new ScratchCard({
    canvas: document.querySelector('#canvas3'),
    coverColor: "#7F7F7F",
});
const card4 = new ScratchCard({
    canvas: document.querySelector('#canvas4'),
    coverColor: "#7F7F7F"  
});
const card5 = new ScratchCard({
    canvas: document.querySelector('#canvas5'),
    coverColor: "#7F7F7F"
});
document.querySelector("#replay").addEventListener("click", () =>  location = location);
