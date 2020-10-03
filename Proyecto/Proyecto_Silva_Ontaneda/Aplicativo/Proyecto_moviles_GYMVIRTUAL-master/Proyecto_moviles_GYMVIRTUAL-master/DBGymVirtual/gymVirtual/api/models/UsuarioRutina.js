/**
 * UsuarioRutina.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    tiempoRutina:{
      type: 'number',
      defaultsTo: 300,
      columnName: 'tiempoRutina'
    },

    usuario:{
      model: 'usuario'
    },

    rutina:{
      model: 'rutina'
    },

    usuarioRutinasEjercicios:{
      collection: 'usuarioRutinaEjercicio',
      via: 'usuarioRutina'
    },
  },

};

