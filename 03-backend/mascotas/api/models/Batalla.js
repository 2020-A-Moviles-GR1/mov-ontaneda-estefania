/**
 * Batalla.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
      lugar:{
        type: 'string'
      },

      pokemon:{
        collection: 'pokemon',
        via: 'batalla'
        //required : true
        //not cuz plural associations can only be populated.
      }

  },

};

