import React from "react";
import Button from "react-bootstrap/lib/Button";
import FormGroup from "react-bootstrap/lib/FormGroup";
import Form from "react-bootstrap/lib/Form";
import FormControl from "react-bootstrap/lib/FormControl";
import ControlLabel from "react-bootstrap/lib/ControlLabel";
import Panel from "react-bootstrap/lib/Panel";
import styled from "styled-components";
import { Modal } from "react-overlays";

import MangaUrl from "./MangaUrl.jsx";
import LoginForm from "./LoginForm.jsx";

const backdropStyle = {
  position: "fixed",
  zIndex: 1040,
  top: 0,
  bottom: 0,
  left: 0,
  right: 0,
  backgroundColor: "#000",
  opacity: 0.5
};

const modalStyle = function() {
  // we use some psuedo random coords so nested modals
  // don't sit right on top of each other.
  let top = 50;
  let left = 50;

  return {
    position: "fixed",
    width: 400,
    zIndex: 1040,
    top: top + "%",
    left: left + "%",
    border: "1px solid #e5e5e5",
    backgroundColor: "white",
    boxShadow: "0 5px 15px rgba(0,0,0,.5)",
    padding: 20
  };
};

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false
    };

    this.close = () => {
      this.setState({ showModal: false });
    };

    this.open = () => {
      this.setState({ showModal: true });
    };
  }

  renderBackdrop(props) {
    return <div {...props} style={backdropStyle} />;
  }

  render() {
    return (
      <div style={{ textAlign: "center" }}>
        <h1>Welcome to the Send To Reader</h1>
        <Button bsStyle="primary" onClick={this.open}>
          Connect
        </Button>
        <Modal
          onHide={this.close}
          style={modalStyle()}
          aria-labelledby="modal-label"
          show={this.state.showModal}
          renderBackdrop={this.renderBackdrop}
        >
          <div>
            <h4 id="modal-label">Text in a modal</h4>
            <p>
              Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
            </p>
          </div>
        </Modal>

        <LoginForm />
        <MangaUrl />
      </div>
    );
  }
}
