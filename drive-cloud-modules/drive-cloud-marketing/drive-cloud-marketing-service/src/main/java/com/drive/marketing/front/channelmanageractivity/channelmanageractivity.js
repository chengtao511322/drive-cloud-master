import request from '@/utils/request'


// 查询渠道经理 可推广表配置列表
export function listChannelManagerActivity(query) {
  return request({
    url: '/marketing/channelManagerActivity/pageList',
    method: 'get',
    params: query
  })
}

// 查询渠道经理 可推广表配置详细
export function getChannelManagerActivity(channelManagerId) {
  return request({
    url: '/marketing/channelManagerActivity/' + channelManagerId,
    method: 'get'
  })
}

// 新增渠道经理 可推广表配置
export function addChannelManagerActivity(data) {
  return request({
    url: '/marketing/channelManagerActivity',
    method: 'post',
    data: data
  })
}

// 修改渠道经理 可推广表配置
export function updateChannelManagerActivity(data) {
  return request({
    url: '/marketing/channelManagerActivity',
    method: 'put',
    data: data
  })
}

// 删除渠道经理 可推广表配置
export function delChannelManagerActivity(channelManagerId) {
  return request({
    url: '/marketing/channelManagerActivity/' + channelManagerId,
    method: 'delete'
  })
}

// 导出渠道经理 可推广表配置
export function exportChannelManagerActivity(query) {
  return request({
    url: '/marketing/channelManagerActivity/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}