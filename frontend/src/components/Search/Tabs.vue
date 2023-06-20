<template>
  <ul class="search-tabs">
    <li
      class="search-tabs__item"
      v-for="(item, index) in tabs"
      :key="index"
      :class="{ active: item.id === tabSelect }"
      @click="changeTab(item.id)"
    >
      {{ currentTranslations === 'Русский' ? item.text : item.textEng  }}
    </li>
  </ul>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'SearchTabs',
  computed: {
    ...mapGetters('global/search', ['tabs', 'tabSelect']),

    currentTranslations() {
      return this.$store.state.auth.languages.language.name;
    },

  },
  methods: {
    ...mapActions('global/search', ['changeTab']),
  },
};
</script>

<style lang="stylus">
@import '../../assets/stylus/base/vars.styl'

.search-tabs
  display flex
  align-items center
  gap 15px
  margin-bottom 15px

.search-tabs__item
  display block
  min-width 120px
  max-width 120px
  color ui-cl-color-767676
  border-radius border-super-small
  text-align center
  background ui-cl-color-white-theme
  border 1px solid ui-cl-color-ababab
  font-size font-size-small
  padding 10px
  cursor pointer
  transition all .2s ease-in-out

  @media (any-hover: hover)
    &:hover
      color ui-cl-color-dark-grey
      background ui-cl-color-c8ecd8
      border-color ui-cl-color-c8ecd8


  &.active
    color ui-cl-color-white-theme
    background ui-cl-color-eucalypt
    border-color ui-cl-color-eucalypt
    &:hover
      color ui-cl-color-white-theme
      background ui-cl-color-eucalypt
      border-color ui-cl-color-eucalypt
</style>
