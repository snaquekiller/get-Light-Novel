import ReactDOM from 'react-dom';
import React from 'react';
import App from 'components/App';
import { Provider } from 'mobx-react';
import ClickStore from 'stores/click';
import makeRoutes from 'libs/routes';

const store = new ClickStore();
const routes = makeRoutes(store);

document.addEventListener('DOMContentLoaded', () => {
  ReactDOM.render(
    <Provider store={store}>
      <App routes={routes} />
    </Provider>, document.querySelector('#app'));
});
