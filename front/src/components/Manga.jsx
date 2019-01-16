import React from "react";
import PropTypes from "prop-types";

import Panel from "react-bootstrap/lib/Panel";
import Image from "react-bootstrap/lib/Image";
import ListGroupItem from "react-bootstrap/lib/ListGroupItem";
import ListGroup from "react-bootstrap/lib/ListGroup";
import styled from "styled-components";

const MangaDiv = styled.div`
  width: 400px;
  img {
    max-width: 200px;
    margin-left: auto;
    margin-right: auto;
  }

  .mangaName {
    font-weight: bold;
    font-size: 20px;
    mcolor: #050c84;
  }
  .title {
    font-weight: bold;
    mcolor: #050c84;
  }

  .text {
    font-weight: normal;
    color: #333;
  }
  .list-group {
    max-width: 150px;
    margin-left: auto;
    margin-right: auto;
    img {
      padding: 5px 15px;
    }
  }
`;

export default class Manga extends React.Component {
  render() {
    return (
      <MangaDiv>
        <Panel>
          <Image src={this.props.manga.image} responsive />
          <div className="mangaName">{this.props.manga.name} </div>
          <span className="title">Author:</span>
          <span className="text"> {this.props.manga.author}</span>
          <ListGroup>
            {!this.props.manga.chapters
              ? ""
              : this.props.manga.chapters.map(url => {
                  let chapterNum = url.split("/");
                  chapterNum = chapterNum[chapterNum.length - 1];
                  return (
                    <ListGroupItem href={url} active key={chapterNum}>
                      Chapter {chapterNum}
                    </ListGroupItem>
                  );
                })}
          </ListGroup>
        </Panel>
      </MangaDiv>
    );
  }
}
Manga.propTypes = {
  manga: PropTypes.shape({
    name: PropTypes.string.isRequired,
    author: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    chapters: PropTypes.array
  })
};
