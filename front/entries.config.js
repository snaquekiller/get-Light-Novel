module.exports = {
  vendors: [
    'babel-polyfill',
    'bootstrap-loader',
    'font-awesome-webpack'
  ],
  app: ['./index.jsx'],
  dashboard: ['./views/dashboard/index.jsx'],
  socialInbox: ['./views/inbox/index.jsx'],
  posts: ['./views/analyse/post/list/index.jsx'],
  coldpost: ['./views/coldPost/index.jsx'],
  publishSlotSetting: ['./views/publishSlotSetting/index.jsx'],
  publish: ['./views/publish/index.jsx'],
  templates: ['./views/publish/templates/index.jsx'],
  post: ['./views/publish/detail/index.jsx']
};
