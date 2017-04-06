module.exports = function foo(config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],
        plugins: [
            require('karma-jasmine'),
            require('karma-chrome-launcher'),
            require('karma-firefox-launcher'),
            require('karma-safari-launcher'),
            require('karma-ie-launcher'),
            require('karma-phantomjs-launcher')
        ],
        files: [
            {pattern: main('lib/rxjs/Rx.js'), watched: false},
            {pattern: test('kotlin.js'), watched: false},
            {pattern: main('kotlin-rxjs.js'), watched: false},
            {pattern: test('kotlin-rxjs-test.js'), watched: false}
        ],
        proxies: {
            '/': '/base'
        },
        client: {
            clearContext: false
        },
        port: 8765,
        logLevel: config['LOG_INFO'],
        browsers: ['Chrome'],
        colors: false,
        singleRun: true
    })
};

function test(file) {
    return './' + file;
}

function main(file) {
    return '../js/' + file;
}