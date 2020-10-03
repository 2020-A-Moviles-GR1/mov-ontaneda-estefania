/**
 * Rutina.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre_rutina:{
      type: 'string',
      required: true,
      columnName: 'nombreRutina',
      maxLength:70
    },
    categoria_rutina:{
      type: 'string',
      columnName: 'categoriaRutina',
      maxLength:150, 
      defaultsTo: 'TotalBody'
    },

    
    usuarioRutinas:{
      collection: 'usuarioRutina',
      via: 'rutina'
    },
    ejercicios:{
      collection: 'ejercicio',
        via: 'rutina'
    }
  },

};

