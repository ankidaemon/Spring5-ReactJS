var path = require("path");
var webpack = require("webpack");
module.exports= {
  entry: path.resolve(__dirname, 'src/index.js'),
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js',
    publicPath: '/'
  },
  module: {
    loaders: [{
      test: /\.js?$/,   /** Regular expression to scan for files */
      exclude: /node_modules/,
      loader: 'babel-loader',
      query: {
          presets: ["react","es2015","stage-2"]
      }
    }]
  }
};