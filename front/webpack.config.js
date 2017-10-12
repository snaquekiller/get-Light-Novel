const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const env = process.env.NODE_ENV || 'development';

const isBuild = env === 'production' || process.env.BUILD;

const clean = list => list.filter(l => l);

const withBuild = module => (isBuild ? module : null);

const entries = require('./entries.config');

module.exports = {
  context: path.join(__dirname, './src/www'),
  entry: entries,
  output: {
    publicPath: '/',
    path: path.join(__dirname, './public'),
    filename: '[name].bundle.js'
  },
  resolve: {
    root: [path.resolve('./src/www'), path.resolve('./node_modules')],
    extensions: ['', '.js', '.jsx', '.css', '.gif']
  },
  devtool: isBuild ? 'source-map' : 'eval',
  module: {
    loaders: [
      { test: /\.json$/, loader: 'json-loader' },
      { test: /\.jsx?$/, loader: 'babel', exclude: /node_modules/ },
      { test: /\.scss$/, loaders: ['style', 'css', 'resolve-url', 'sass'] },
      // { test: /\.html$/, loader: 'raw' },
      { test: /\.css$/, loader: 'style!css' },
      { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: 'url-loader?limit=10000&minetype=application/font-woff'
      },
      { test: /\.gif$/, loader: 'url?limit=10000&minetype=image/gif' },
      { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: 'file-loader' },
      { test: /\.jpg$/, loader: 'url?limit=10000&minetype=image/jpg' },
      { test: /\.png$/, loader: 'url?limit=10000&minetype=image/png' },
      { test: /\.ico/, loader: 'url?limit=10000&minetype=image/x-icon' },
    ]
  },
  sassLoader: {
    // includePaths: [path.resolve(__dirname, './node_modules/bootstrap-sass/assets/stylesheets/')]
  },
  plugins: clean([
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery'
    }),
    new webpack.optimize.CommonsChunkPlugin('core.bundle.js'),
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify(env),
        WITH_NK: JSON.stringify(process.env.WITH_NK)
      }
    }),
    new HtmlWebpackPlugin({
      template: 'index.html',
      inject: 'body',
      minify: {
        removeComments: true,
        collapseWhitespace: true,
        removeRedundantAttributes: true,
        useShortDoctype: true,
        removeEmptyAttributes: true,
        removeStyleLinkTypeAttributes: true,
        keepClosingSlash: true,
        minifyJS: true,
        minifyCSS: true,
        minifyURLs: true
      }
    }),
    withBuild(new webpack.optimize.OccurenceOrderPlugin()),
    withBuild(new webpack.NoErrorsPlugin()),
    withBuild(new webpack.optimize.DedupePlugin()),
    withBuild(new webpack.optimize.UglifyJsPlugin({
      minimize: true,
      compress: {
        screw_ie8: true,
        warnings: false
      },
      mangle: {
        screw_ie8: true
      },
      output: {
        screw_ie8: true,
        comments: false
      }
    })),
    withBuild(new CopyWebpackPlugin([
            { from: 'assets/fonts', to: 'assets/fonts' },
            { from: 'assets/images', to: 'assets/images' }
    ])),
  ])
};
