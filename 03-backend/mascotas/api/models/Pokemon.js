/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre:{
      type: 'string'
    },
    usuario:{ //Many to one (nombreFK) -- Mismo nombre de la relacion
      model: 'usuario',
     // required: true //es opcional 1 muchos o 0 a muchos  
    },

    batalla:{
      model: 'batalla'
    }
  },

};

