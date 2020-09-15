/**
 * Comic.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombreComic:{
      type: 'string', 
      maxLength:50,
      required: true

    },

    vigencia:{
      type: 'string', 
    },

    paginas:{
      type: 'string',
      required: true
    },

    precio:{
      type: 'string',
      required: true
    }
  },

};

