module.exports = function (config) {
  config.set({
    basePath: './',
    frameworks: [
      'jasmine'
    ],
    plugins: [
      'karma-jasmine',
      'karma-phantomjs-launcher'
    ],
    files: [
      { pattern: 'target/test-js/kotlin-rxjs-test.bundle.js', watched: false, included: true, served: true , nocache: false }
    ],
    client: {
      clearContext: false
    },
    port: 9876,
    logLevel: config['LOG_INFO'],
    browsers: [
      'PhantomJS'
    ],
    colors: false,
    singleRun: true
  });
};