import request from '@/utils/request'


// 分页查询活动推广权限配置列表
export function listPageActivityPromoteAuth(query) {
  return request({
    url: '/marketing/activityPromoteAuth/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询活动推广权限配置列表
export function listActivityPromoteAuth(query) {
  return request({
    url: '/marketing/activityPromoteAuth/findList',
    method: 'post',
    params: query
  })
}

// 查询活动推广权限配置详细
export function getActivityPromoteAuth(${pkColumn.propertyName}) {
  return request({
    url: '/marketing/activityPromoteAuth/' + ${pkColumn.propertyName},
    method: 'get'
  })
}

// 新增活动推广权限配置
export function addActivityPromoteAuth(data) {
  return request({
    url: '/marketing/activityPromoteAuth',
    method: 'post',
    data: data
  })
}

// 修改活动推广权限配置
export function updateActivityPromoteAuth(data) {
  return request({
    url: '/marketing/activityPromoteAuth',
    method: 'put',
    data: data
  })
}

// 删除活动推广权限配置
export function delActivityPromoteAuth(${pkColumn.propertyName}) {
  return request({
    url: '/marketing/activityPromoteAuth/' + ${pkColumn.propertyName},
    method: 'delete'
  })
}


// 状态启用/停用活动推广权限配置
export function changeStatus(data) {
  return request({
    url: '/marketing/activityPromoteAuth/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出活动推广权限配置
export function exportActivityPromoteAuth(query) {
  return request({
    url: '/marketing/activityPromoteAuth/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}